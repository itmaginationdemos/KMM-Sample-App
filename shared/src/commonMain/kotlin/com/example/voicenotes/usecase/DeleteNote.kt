package com.example.voicenotes.usecase

import android.util.Log
import com.example.voicenotes.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteNote(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(id: String) {
        return withContext(Dispatchers.IO) {
            repository.deleteNote(id)
        }
    }
}
