package com.example.voicenotes.kmm.shared.cache

import com.example.voicenotes.model.NoteResource
import com.squareup.sqldelight.db.SqlDriver

class Database(driver: SqlDriver) {
    private val database = AppDatabase(driver)
    private val dbQuery = database.appDatabaseQueries

    suspend fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllNotes()
        }
    }

    fun getAllNotes(): List<NoteResource> {
        return dbQuery.selectAllNotes(::mapToResource).executeAsList()
    }

    suspend fun getNoteWithId(id: String): NoteResource? {
        return dbQuery.selectNoteById(id.toLong()).executeAsOneOrNull()?.toResource()
    }

    suspend fun insertNote(note: NoteResource) {
        dbQuery.insertNotes(
            id = null,
            title = note.title,
            content = note.content,
            filePath = note.filePath
        )
    }

    suspend fun deleteNote(id: String) {
        dbQuery.removeNoteById(id.toLong())
    }
}

private fun NoteLocalResource.toResource() =
    NoteResource(
        id = id.toString(),
        title = title ?: "",
        content = content ?: "",
        filePath = filePath
    )

fun mapToResource(id: Long, title: String?, content: String?, filePath: String?): NoteResource =
    NoteResource(
        id = id.toString(),
        title = title ?: "",
        content = content ?: "",
        filePath = filePath
    )
