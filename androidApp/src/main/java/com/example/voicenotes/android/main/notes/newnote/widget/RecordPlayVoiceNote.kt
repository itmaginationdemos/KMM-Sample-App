package com.example.voicenotes.android.main.notes.newnote.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.voicenotes.android.R

@Composable
fun PlayVoiceNote(
    toggle: (Boolean, () -> Unit) -> Unit
) {
    val texts = Pair(R.string.play_voice_note, R.string.description_play_note)
    val icons = Pair(R.drawable.ic_play, R.drawable.ic_stop_record)

    var isPlaying by remember { mutableStateOf(false) }
    val id by remember(isPlaying) {
        derivedStateOf { if (isPlaying) icons.second else icons.first }
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(
            onClick = {
                isPlaying = !isPlaying
                toggle(isPlaying) {
                    isPlaying = false // on complete
                }
            }
        ) {
            Icon(
                painter = painterResource(id),
                contentDescription = stringResource(texts.second),
                modifier = Modifier.padding(8.dp)
            )
        }
        Text(text = stringResource(texts.first))
    }
}

@Composable
fun RecordVoiceNote(
    toggle: (Boolean) -> Unit,
    hasPermission: Boolean,
    onNoPermission: () -> Unit
) {
    val texts = Pair(R.string.record_voice_note, R.string.description_record_note)
    val icons = Pair(R.drawable.ic_record_voice, R.drawable.ic_stop_record)

    var isRecording by remember { mutableStateOf(false) }
    val id by remember(isRecording) {
        derivedStateOf { if (isRecording) icons.second else icons.first }
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(
            onClick = {
                if (hasPermission) {
                    isRecording = !isRecording
                    toggle(isRecording)
                } else {
                    onNoPermission()
                }
            }
        ) {
            Icon(
                painter = painterResource(id),
                contentDescription = stringResource(texts.second),
                modifier = Modifier.padding(8.dp)
            )
        }
        Text(text = stringResource(texts.first))
    }
}
