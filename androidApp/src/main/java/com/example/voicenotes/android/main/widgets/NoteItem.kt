package com.example.voicenotes.android.main.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.voicenotes.model.Note
import com.example.voicenotes.model.NoteLength
import com.example.voicenotes.model.NoteLengthType

@Composable
fun NoteItem(note: Note) {
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Title(note.title)
            LengthBubble(note.noteLength)
            Content(note.content)
        }
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.subtitle1
    )
}

@Composable
fun Content(content: String) {
    Text(
        text = content,
        modifier = Modifier.padding(4.dp),
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun LengthBubble(noteLength: NoteLength) {
    Card(
        elevation = 4.dp
    ) {
        Text(
            text = noteLength.label,
            modifier = Modifier.background(resolveColor(noteLength.length)).padding(8.dp)
        )
    }
}

fun resolveColor(length: NoteLengthType): Color =
    when (length) {
        NoteLengthType.LONG -> Color.Red
        NoteLengthType.MEDIUM -> Color.Yellow
        NoteLengthType.SHORT -> Color.Green
    }