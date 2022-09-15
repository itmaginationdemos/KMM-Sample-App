package com.example.voicenotes.android.main.notes.newnote

import android.Manifest
import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.voicenotes.android.R
import com.example.voicenotes.android.main.notes.helpers.togglePlay
import com.example.voicenotes.android.main.notes.helpers.toggleRecord
import com.example.voicenotes.android.main.notes.newnote.widget.PlayVoiceNote
import com.example.voicenotes.android.main.notes.newnote.widget.RecordVoiceNote
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NewNoteForm(
    state: NewFormState,
    onEvent: (NewNoteEvent) -> Unit
) {
    var recording by remember { mutableStateOf(false) }
    val permission = rememberPermissionState(Manifest.permission.RECORD_AUDIO)
    val context = LocalContext.current
    val file = remember { File(context.filesDir, System.nanoTime().toString() + ".3gp") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .scale(scaleY = 0.9F, scaleX = 1F),
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
            modifier = Modifier
                .fillMaxWidth()
                .scale(scaleY = 0.9F, scaleX = 1F),
            value = state.content,
            label = { Text(text = stringResource(id = R.string.content_label)) },
            colors =
            TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                textColor = MaterialTheme.colors.primary
            ),
            onValueChange = { newSearch -> onEvent(NewNoteEvent.OnContentUpdate(newSearch)) }
        )
        Spacer(modifier = Modifier.height(4.dp))
        RecordVoiceNote(
            toggle = { toggle ->
                recording = toggle
                toggleRecord(toggle, file, context)
            },
            hasPermission = permission.status == PermissionStatus.Granted,
            onNoPermission = { permission.launchPermissionRequest() }
        )
        if (!recording && permission.status == PermissionStatus.Granted) {
            PlayVoiceNote { toggle, lambda ->
                togglePlay(togglePlaying = toggle, uri = file.path, onCompleted = lambda)
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            enabled = state.enabled,
            onClick = { onEvent(NewNoteEvent.OnSave(file.path)) }
        ) {
            Text(text = stringResource(id = R.string.create_note))
        }
    }
}
