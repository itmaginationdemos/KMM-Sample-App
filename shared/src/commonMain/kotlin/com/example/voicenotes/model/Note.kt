package com.example.voicenotes.model

data class Note(
    val id: Long,
    val title: String,
    val content: String,
    val noteLength: NoteLength
)

fun NoteResource.toNote(): Note {
    fun getFromContent(content: String): NoteLength {
        return when (content.length) {
            in 0..100 -> NoteLength("Short", NoteLengthType.SHORT)
            in 101..300 -> NoteLength("Medium", NoteLengthType.MEDIUM)
            else -> NoteLength("Long", NoteLengthType.LONG)
        }
    }

    return Note(
        title = title,
        content = content,
        id = id,
        noteLength = getFromContent(content)
    )
}
