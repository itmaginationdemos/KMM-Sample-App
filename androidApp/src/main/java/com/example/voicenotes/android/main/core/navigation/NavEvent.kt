package com.example.voicenotes.android.main.core.navigation

sealed interface NavEvent {
    object CloseNewNote : NavEvent
    data class Record(val isRecording: Boolean) : NavEvent
}
