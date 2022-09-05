package com.example.voicenotes.usecase

import com.example.voicenotes.model.Note
import com.example.voicenotes.model.NoteLength
import com.example.voicenotes.model.NoteLengthType
import com.example.voicenotes.repository.NotesRepository

class GetNotes(
    private val repository: NotesRepository
) {
    suspend operator fun invoke() =
        repository.getNotes().map {
            Note(
                title = it.title,
                content = it.content,
                id = it.id,
                noteLength = getFromContent(it.content)
            )
        }

    private fun getFromContent(content: String): NoteLength {
        return when (content.length) {
            in 0..100 -> NoteLength("Short", NoteLengthType.SHORT)
            in 101..300 -> NoteLength("Medium", NoteLengthType.MEDIUM)
            else -> NoteLength("Long", NoteLengthType.LONG)
        }
    }
}
