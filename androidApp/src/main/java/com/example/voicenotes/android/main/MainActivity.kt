package com.example.voicenotes.android.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.voicenotes.android.main.core.constants.notesDetailScreen
import com.example.voicenotes.android.main.core.constants.notesDetailScreen_args
import com.example.voicenotes.android.main.core.constants.notesListScreen
import com.example.voicenotes.android.main.notes.detail.NotesDetail
import com.example.voicenotes.android.main.notes.detail.NotesDetailViewModel
import com.example.voicenotes.android.main.notes.list.NotesList
import com.example.voicenotes.android.main.notes.list.NotesListViewModel
import org.koin.androidx.compose.getViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { AppNavHost() }
    }
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = notesListScreen
        ) {
            composable(notesListScreen) {
                val vm = getViewModel<NotesListViewModel>()
                NotesList(
                    list = vm.notes.value,
                    navController = navController,
                    onDeleteClicked = { (vm::onDeleteClicked)(it) }
                )
            }
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
