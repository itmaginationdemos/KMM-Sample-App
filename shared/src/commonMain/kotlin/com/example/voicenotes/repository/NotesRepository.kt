package com.example.voicenotes.repository

import com.example.voicenotes.kmm.shared.cache.Database
import com.example.voicenotes.model.NoteResource
import kotlin.random.Random

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

    suspend fun getNote(id: String): NoteResource? {
        return db.getNoteWithId(id)
    }

    suspend fun deleteNote(id: String): List<NoteResource> {
        db.deleteNote(id)
        return getNotes(true)
    }

    fun generateNote(): NoteResource {
        val new = NoteResource(
            Random.nextInt().toString(),
            "Generic title",
            "Generic content"
        )
        db.insertNote(new)
        return new
    }
}
