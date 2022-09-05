package com.example.voicenotes.usecase

import com.example.voicenotes.model.toNote
import com.example.voicenotes.repository.NotesRepository

class GetNote(
    private val repository: NotesRepository
) {
    operator fun invoke(id: String?) = repository.getNote(id).toNote()
}
