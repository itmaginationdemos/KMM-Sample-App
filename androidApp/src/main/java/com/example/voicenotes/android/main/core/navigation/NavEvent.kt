package com.example.voicenotes.android.main.core.navigation

sealed interface NavEvent {
    object CloseNewNote : NavEvent
}
