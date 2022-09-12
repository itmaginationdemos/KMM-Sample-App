package com.example.voicenotes.android.main.notes.newnote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.voicenotes.android.R

@Composable
fun NewNoteForm(
    state: NewFormState,
    onEvent: (NewNoteEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth().scale(scaleY = 0.9F, scaleX = 1F),
            value = state.title,
            label = { Text(text = stringResource(id = R.string.title_label)) },
            colors =
            TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                textColor = MaterialTheme.colors.primary
            ),
            onValueChange = { newSearch -> onEvent(NewNoteEvent.OnTitleUpdate(newSearch)) }
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextField(
            modifier = Modifier.fillMaxWidth().scale(scaleY = 0.9F, scaleX = 1F),
            value = state.content,
            label = { Text(text = stringResource(id = R.string.content_label)) },
            colors =
            TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                textColor = MaterialTheme.colors.primary
            ),
            onValueChange = { newSearch -> onEvent(NewNoteEvent.OnContentUpdate(newSearch)) }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            enabled = state.enabled,
            onClick = { onEvent(NewNoteEvent.OnSave) }
        ) {
            Text(text = stringResource(id = R.string.create_note))
        }
    }
}
