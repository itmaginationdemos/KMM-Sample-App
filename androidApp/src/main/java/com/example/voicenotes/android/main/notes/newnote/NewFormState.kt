package com.example.voicenotes.android.main.notes.newnote

data class NewFormState(
    val title: String = "",
    val content: String = "",
    val enabled: Boolean = false,
    val isDone: Boolean = false
)

sealed interface NewNoteEvent {
    data class OnTitleUpdate(val title: String) : NewNoteEvent
    data class OnContentUpdate(val content: String) : NewNoteEvent
    object OnSave : NewNoteEvent
}
