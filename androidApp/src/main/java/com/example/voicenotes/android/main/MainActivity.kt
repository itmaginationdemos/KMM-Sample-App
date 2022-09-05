package com.example.voicenotes.android.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.getViewModel
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
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppNavHost(viewModel)
        }
    }
}

@Composable
fun AppNavHost(viewModel: MainViewModel) {
    val navController = rememberNavController()

    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = notesListScreen
        ) {
            composable(notesListScreen) {
                NotesList(
                    list = viewModel.notes.value,
                    navController = navController
                )
            }
            composable(
                route = "$notesDetailScreen/{$notesDetailScreen_args}",
                arguments = listOf(navArgument(notesDetailScreen_args) { type = NavType.StringType })
            ) { entry ->
                val vm = getViewModel<NotesDetailViewModel>()
                NotesDetail(note = (vm::getNoteWith)(entry.arguments?.getString(notesDetailScreen_args)))
            }
        }
    }
}
