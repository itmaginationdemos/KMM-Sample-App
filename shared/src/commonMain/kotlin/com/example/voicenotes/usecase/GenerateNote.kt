package com.example.voicenotes.usecase

import com.example.voicenotes.model.NoteResource
import com.example.voicenotes.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GenerateNote(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(
        title: String,
        content: String
    ) {
        withContext(Dispatchers.IO) {
            val new = NoteResource(
                null,
                title,
                content
            )
            repository.generateNote(new)
        }
    }
}
