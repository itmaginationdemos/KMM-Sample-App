package com.example.voicenotes.android.main.notes.helpers

import android.media.MediaPlayer

private var player: MediaPlayer? = null

fun togglePlay(
    togglePlaying: Boolean,
    uri: String?,
    onCompleted: () -> Unit
) {
    if (togglePlaying) {
        player = MediaPlayer().apply {
            try {
                setDataSource(uri)
                prepare()
                start()
            } catch (_: Exception) {
            }

            this.setOnCompletionListener {
                this.stop()
                onCompleted()
            }
        }
    } else {
        player?.release()
        player = null
    }
}
