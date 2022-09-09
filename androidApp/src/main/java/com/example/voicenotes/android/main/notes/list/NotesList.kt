package com.example.voicenotes.android.main.notes.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.voicenotes.android.main.notes.list.widgets.NoteItem
import com.example.voicenotes.model.Note

@Composable
fun NotesList(
    list: List<Note>,
    navController: NavController,
    onDeleteClicked: (String) -> Unit,
    onAddClicked: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddClicked() }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add note",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
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
}
