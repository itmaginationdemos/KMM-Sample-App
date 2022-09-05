package com.example.voicenotes.android.main.notes.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.voicenotes.android.main.widgets.NoteItem
import com.example.voicenotes.model.Note

@Composable
fun NotesList(
    list: List<Note>,
    navController: NavController,
    onDeleteClicked: (String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(list) { note ->
            NoteItem(
                note = note,
                onItemClicked = { route -> navController.navigate(route) },
                onDeleteClicked = onDeleteClicked
            )
        }
    }
}
