package com.example.voicenotes.repository

import com.example.voicenotes.kmm.shared.cache.Database
import com.example.voicenotes.model.NoteResource

class NotesRepository(
    private val db: Database
) {

    private var notes: List<NoteResource> = mutableListOf()

    suspend fun getNotes(force: Boolean = false): List<NoteResource> {
        if (notes.isEmpty() || force) {
            notes = db.getAllNotes().sortedBy { note -> note.id.toInt() }
        }
        return notes
    }

    suspend fun getNote(id: String?): NoteResource {
        if (id == null) return notes.first()
        return notes.first { it.id == id }
    }

    suspend fun deleteNote(id: String): List<NoteResource> {
        db.deleteNote(id)
        return getNotes(true)
    }
}
