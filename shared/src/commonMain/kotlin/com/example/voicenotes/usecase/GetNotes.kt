package com.example.voicenotes.usecase

import com.example.voicenotes.model.toNote
import com.example.voicenotes.repository.NotesRepository

class GetNotes(
    private val repository: NotesRepository
) {
    suspend operator fun invoke() =
        repository.getNotes().map {
            it.toNote()
        }
}
