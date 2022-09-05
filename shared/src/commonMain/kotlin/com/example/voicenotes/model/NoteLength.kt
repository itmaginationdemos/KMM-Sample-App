package com.example.voicenotes.model

data class NoteLength(
    val label: String,
    val length: NoteLengthType
)

enum class NoteLengthType {
    LONG, MEDIUM, SHORT
}
