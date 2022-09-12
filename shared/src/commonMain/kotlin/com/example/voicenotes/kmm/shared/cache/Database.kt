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
            content = note.content
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
        content = content ?: ""
    )

fun mapToResource(id: Long, title: String?, content: String?): NoteResource =
    NoteResource(
        id = id.toString(),
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
