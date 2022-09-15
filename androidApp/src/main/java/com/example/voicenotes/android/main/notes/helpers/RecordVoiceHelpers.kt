package com.example.voicenotes.android.main.notes.helpers

import android.content.Context
import android.media.MediaRecorder
import java.io.File

private var recorder: MediaRecorder? = null

fun toggleRecord(toggleRecording: Boolean, file: File, context: Context) {
    if (toggleRecording) {
        recorder = initRecorder(file, context)
        try {
            recorder?.prepare()
        } catch (_: Exception) {
        }
        recorder?.start()
    } else {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }
}

private fun initRecorder(file: File, context: Context): MediaRecorder {
    return MediaRecorder(context).apply {
        setAudioSource(MediaRecorder.AudioSource.MIC)
        setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        setOutputFile(file)
        setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
    }
}
