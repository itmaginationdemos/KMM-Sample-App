package com.example.voicenotes.usecase

import com.example.voicenotes.model.toNote
import com.example.voicenotes.repository.NotesRepository
import kotlinx.coroutines.flow.map

class StreamNotes(
    private val repository: NotesRepository
) {
    operator fun invoke() =
        repository.streamNotes()
            .map { notes -> notes.map { it.toNote() } }
}
