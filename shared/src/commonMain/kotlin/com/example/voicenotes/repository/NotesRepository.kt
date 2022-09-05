package com.example.voicenotes.repository

import com.example.voicenotes.model.NoteResource
import kotlinx.coroutines.delay

class NotesRepository {

    private var notes = emptyList<NoteResource>()

    // todo add sql delight stuff
    // https://play.kotlinlang.org/hands-on/Networking%20and%20Data%20Storage%20with%20Kotlin%20Multiplatfrom%20Mobile/05_Configuring_SQLDelight_an_implementing_cache

    suspend fun getNotes(): List<NoteResource> {
        delay(2000)
        notes = listOf(note1, note2, note3, note2, note3)
        return notes
    }

    fun getNote(id: String?): NoteResource {
        if (id == null) return notes.first()
        return notes.first { it.id.toString() == id }
    }
}

private val note1 = NoteResource(
    1L,
    "Note1",
    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
)
private val note2 = NoteResource(
    2L,
    "Note2",
    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
)
private val note3 = NoteResource(
    3L,
    "Note3",
    "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
)
