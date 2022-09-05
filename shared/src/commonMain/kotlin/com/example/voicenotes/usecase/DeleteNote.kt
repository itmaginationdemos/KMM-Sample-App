package com.example.voicenotes.usecase

import com.example.voicenotes.model.toNote
import com.example.voicenotes.repository.NotesRepository

class DeleteNote(
    private val repository: NotesRepository
) {
    operator fun invoke(id: String) = repository.deleteNote(id).map { it.toNote() }
}
