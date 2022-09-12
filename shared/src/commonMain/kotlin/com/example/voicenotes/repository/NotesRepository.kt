package com.example.voicenotes.repository

import com.example.voicenotes.kmm.shared.cache.Database
import com.example.voicenotes.model.NoteResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class NotesRepository(
    private val db: Database
) {

    private var notes: MutableStateFlow<List<NoteResource>> = MutableStateFlow(listOf())

    init {
        notes.tryEmit(getAllNotes())
    }

    private fun getAllNotes() =
        db.getAllNotes().sortedBy { it.id?.toInt() }

    suspend fun getNote(id: String): NoteResource? {
        return db.getNoteWithId(id)
    }

    suspend fun deleteNote(id: String) {
        db.deleteNote(id)
        notes.tryEmit(
            notes.value.filter { it.id != id }
        )
    }

    suspend fun generateNote(new: NoteResource) {
        db.insertNote(new)
        notes.tryEmit(getAllNotes())
    }

    fun streamNotes(): Flow<List<NoteResource>> {
        return notes
    }
}
