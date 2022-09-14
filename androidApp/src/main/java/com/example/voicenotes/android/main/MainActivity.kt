package com.example.voicenotes.android.main

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.media.MediaRecorder.AudioSource.MIC
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.voicenotes.android.main.core.constants.newNoteScreen
import com.example.voicenotes.android.main.core.constants.notesDetailScreen
import com.example.voicenotes.android.main.core.constants.notesDetailScreen_args
import com.example.voicenotes.android.main.core.constants.notesListScreen
import com.example.voicenotes.android.main.core.navigation.NavEvent
import com.example.voicenotes.android.main.core.navigation.Navigator
import com.example.voicenotes.android.main.notes.detail.NotesDetail
import com.example.voicenotes.android.main.notes.detail.NotesDetailViewModel
import com.example.voicenotes.android.main.notes.list.NotesList
import com.example.voicenotes.android.main.notes.list.NotesListViewModel
import com.example.voicenotes.android.main.notes.newnote.NewNoteForm
import com.example.voicenotes.android.main.notes.newnote.NewNoteViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.getViewModel
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { AppNavHost(navigator = navigator, context = this) }
    }
}

private fun initRecorder(context: Context): MediaRecorder {
    val file = File(context.filesDir, "myVoiceNote.3gp")
    Log.d("karlo", "initRecorder: ${file.absolutePath}")

    return MediaRecorder(context).apply {
        setAudioSource(MIC)
        setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        setOutputFile(file)
        setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        prepare()
    }
}

private fun startPlaying(context: Context): MediaPlayer {
    val uri = "${context.filesDir}/myVoiceNote.3gp"
    return MediaPlayer().apply {
        try {
            setDataSource(uri)
            prepare()
            start()
        } catch (e: IOException) {
            Log.d("karlo", "startPlaying: $e")
        }
    }
}

@Composable
fun AppNavHost(
    navigator: Navigator,
    navController: NavHostController = rememberNavController(),
    context: Context
) {
    var recorder: MediaRecorder? = remember { null }
    var player: MediaPlayer? = remember { null }

    LaunchedEffect(key1 = navigator) {
        navigator.navigationEvent
            .collect {
                when (it) {
                    NavEvent.CloseNewNote -> navController.popBackStack()
                    NavEvent.InitRecorder -> if (recorder == null) recorder = initRecorder(context)
                    is NavEvent.Record -> toggleRecord(recorder, it.isRecording)
                    NavEvent.StartPlaying -> player = startPlaying(context)
                }
            }
    }

    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = notesListScreen
        ) {
            // list screen
            composable(notesListScreen) {
                val vm = getViewModel<NotesListViewModel>()
                NotesList(
                    list = vm.notes.value,
                    navController = navController,
                    onDeleteClicked = { (vm::onDeleteClicked)(it) },
                    onAddClicked = { navController.navigate(newNoteScreen) }
                )
            }

            // new note
            composable(newNoteScreen) {
                val vm = getViewModel<NewNoteViewModel>()
                NewNoteForm(vm.state.value, vm::onEvent)
            }

            // details screen
            composable(
                route = "$notesDetailScreen/{$notesDetailScreen_args}",
                arguments = listOf(
                    navArgument(notesDetailScreen_args) { type = NavType.StringType }
                )
            ) { entry ->
                val vm = getViewModel<NotesDetailViewModel>()
                vm.getNoteWith(entry.arguments?.getString(notesDetailScreen_args))
                NotesDetail(note = vm.note.value)
            }
        }
    }
}

fun toggleRecord(recorder: MediaRecorder?, toggleRecording: Boolean) {
    if (toggleRecording) {
        recorder?.start()
    } else {
        recorder?.stop()
    }
}
