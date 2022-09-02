package com.example.voicenotes.repository

import com.example.voicenotes.model.Note
import kotlinx.coroutines.delay

class NotesRepository {

    // todo add sql delight stuff
    // https://play.kotlinlang.org/hands-on/Networking%20and%20Data%20Storage%20with%20Kotlin%20Multiplatfrom%20Mobile/05_Configuring_SQLDelight_an_implementing_cache

    suspend fun getNotes(): List<Note> {
        delay(2000)
        return listOf(note1, note2, note3)
    }
}

private val note1 = Note(1L, "Note1", "This is note one")
private val note2 = Note(2L, "Note2", "This is note two")
private val note3 = Note(3L, "Note3", "This is note three")