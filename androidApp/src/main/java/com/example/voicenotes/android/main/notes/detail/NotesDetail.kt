package com.example.voicenotes.android.main.notes.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.voicenotes.model.Note

@Composable
fun NotesDetail(
    note: Note?
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = note?.title ?: "")
    }
}
