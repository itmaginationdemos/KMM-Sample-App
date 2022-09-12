package com.example.voicenotes.android.main

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.media.MediaRecorder.AudioSource.MIC
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.app.ActivityCompat
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

class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_RECORD_AUDIO_PERMISSION = 200
        const val REQUEST_WRITE_PERMISSION = 201
        var permissionsToAsk: Array<String> =
            arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    var recorder: MediaRecorder? = null

    private val navigator: Navigator by inject()
    private var permissionToRecordAccepted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { AppNavHost(navigator = navigator, recorder = recorder, context = this) }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionToRecordAccepted =
            if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION && grantResults.isNotEmpty()) {
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            } else if (requestCode == REQUEST_WRITE_PERMISSION && grantResults.isNotEmpty()) {
                initRecorder()
                grantResults[1] == PackageManager.PERMISSION_GRANTED
            } else {
                false
            }
    }

    private fun initRecorder() {
        Log.d("karlo", "initRecorder: ")
        val file = File.createTempFile(
            "test",
            ".3gp",
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_AUDIOBOOKS)
        )

        recorder = MediaRecorder(this).apply {
            setAudioSource(MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(file)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            prepare()
        }
    }
}

@Composable
fun AppNavHost(
    navigator: Navigator,
    navController: NavHostController = rememberNavController(),
    recorder: MediaRecorder?,
    context: Context
) {
    LaunchedEffect(key1 = navigator) {
        navigator.navigationEvent
            .collect {
                when (it) {
                    NavEvent.CloseNewNote -> navController.popBackStack()
                    is NavEvent.Record -> toggleRecord(recorder, it.isRecording)
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
                ActivityCompat.requestPermissions(
                    context as Activity,
                    MainActivity.permissionsToAsk,
                    MainActivity.REQUEST_RECORD_AUDIO_PERMISSION
                )
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

fun toggleRecord(recorder: MediaRecorder?, recording: Boolean) {
    Log.d("karlo", "toggleRecord: $recording")
    Log.d("karlo", "toggleRecord: $recorder")
    if (recording) {
        recorder?.stop()
    } else {
        recorder?.start()
    }
}
