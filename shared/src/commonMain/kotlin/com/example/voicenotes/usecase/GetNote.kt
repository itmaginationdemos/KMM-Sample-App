package com.example.voicenotes.usecase

import com.example.voicenotes.model.Note
import com.example.voicenotes.model.toNote
import com.example.voicenotes.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetNote(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(id: String): Note? {
        return withContext(Dispatchers.IO) {
            repository.getNote(id)?.toNote()
        }
    }
}
