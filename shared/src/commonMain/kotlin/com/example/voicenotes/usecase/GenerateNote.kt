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
        content: String,
        filePath: String
    ) {
        withContext(Dispatchers.Default) {
            val new = NoteResource(
                id = null,
                title = title,
                content = content,
                filePath = filePath
            )
            repository.generateNote(new)
        }
    }
}
