package com.example.voicenotes.kmm.shared.cache

import com.example.voicenotes.model.NoteResource
import com.squareup.sqldelight.db.SqlDriver

class Database(driver: SqlDriver) {
    private val database = AppDatabase(driver)
    private val dbQuery = database.appDatabaseQueries

    init {
        createNotes()
    }

    suspend fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllNotes()
        }
    }

    suspend fun getAllNotes(): List<NoteResource> {
        return dbQuery.selectAllNotes(::mapToResource).executeAsList()
    }

    suspend fun getNoteWithId(id: String): NoteResource? {
        return dbQuery.selectNoteById(id).executeAsOneOrNull()?.toResource()
    }

    private fun createNotes() {
        val notes = listOf(note1, note2, note3, note2.copy(id = "4"), note3.copy(id = "5"))
        notes.forEach { note ->
            val noteLocal = dbQuery.selectNoteById(note.id).executeAsOneOrNull()
            if (noteLocal == null) insertNote(note)
        }
    }

    fun insertNote(note: NoteResource) {
        dbQuery.insertNotes(
            id = note.id,
            title = note.title,
            content = note.content
        )
    }

    suspend fun deleteNote(id: String) {
        dbQuery.removeNoteById(id)
    }
}

private fun NoteLocalResource.toResource() =
    NoteResource(
        id = id,
        title = title ?: "",
        content = content ?: ""
    )

fun mapToResource(id: String, title: String?, content: String?): NoteResource =
    NoteResource(
        id = id,
        title = title ?: "",
        content = content ?: ""
    )

private val note1 = NoteResource(
    "1",
    "Note1",
    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
)
private val note2 = NoteResource(
    "2",
    "Note2",
    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
)
private val note3 = NoteResource(
    "3",
    "Note3",
    "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
)
