package com.example.voicenotes.usecase

import com.example.voicenotes.model.Note
import com.example.voicenotes.model.toNote
import com.example.voicenotes.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GenerateNote(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(): Note {
        return withContext(Dispatchers.IO) {
            repository.generateNote().toNote()
        }
    }
}
