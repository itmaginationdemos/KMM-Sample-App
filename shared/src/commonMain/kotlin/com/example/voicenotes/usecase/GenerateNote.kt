package com.example.voicenotes.usecase

import com.example.voicenotes.model.Note
import com.example.voicenotes.model.NoteResource
import com.example.voicenotes.model.toNote
import com.example.voicenotes.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

class GenerateNote(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(
        title: String,
        content: String
    ): Note {
        return withContext(Dispatchers.IO) {
            val new = NoteResource(
                "",
                title,
                content
            )
            repository.generateNote(new).toNote()
        }
    }
}
