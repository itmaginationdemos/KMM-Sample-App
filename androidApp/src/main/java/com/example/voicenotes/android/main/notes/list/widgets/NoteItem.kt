package com.example.voicenotes.android.main.notes.list.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.voicenotes.android.main.core.constants.notesDetailScreen
import com.example.voicenotes.model.Note
import com.example.voicenotes.model.NoteLength
import com.example.voicenotes.model.NoteLengthType

@Composable
fun NoteItem(
    note: Note,
    onItemClicked: (String) -> Unit,
    onDeleteClicked: (String) -> Unit
) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .clickable { onItemClicked("$notesDetailScreen/${note.id}") },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Title(
                    title = note.title,
                    modifier = Modifier.weight(8f)
                )
                IconButton(
                    onClick = { onDeleteClicked(note.id) },
                    modifier = Modifier.weight(2f)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete note",
                        tint = Color.Red,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            LengthBubble(note.noteLength)
            Content(note.content)
        }
    }
}

@Composable
fun Title(title: String, modifier: Modifier) {
    Text(
        text = title,
        modifier = modifier.padding(8.dp),
        style = MaterialTheme.typography.h5
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
            modifier = Modifier
                .background(resolveColor(noteLength.length))
                .padding(8.dp)
        )
    }
}

fun resolveColor(length: NoteLengthType): Color =
    when (length) {
        NoteLengthType.LONG -> Color.Red
        NoteLengthType.MEDIUM -> Color.Yellow
        NoteLengthType.SHORT -> Color.Green
    }
