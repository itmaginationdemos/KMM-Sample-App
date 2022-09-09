package com.example.voicenotes.android.main.notes.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.voicenotes.android.main.notes.list.widgets.NoteItem
import com.example.voicenotes.model.Note

@Composable
fun NotesDetail(
    note: Note?
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        note?.let {
            NoteItem(
                note = it,
                onItemClicked = null,
                onDeleteClicked = null
            )
        }
    }
}
