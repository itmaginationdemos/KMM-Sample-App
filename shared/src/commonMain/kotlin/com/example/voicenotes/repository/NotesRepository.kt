package com.example.voicenotes.repository

import com.example.voicenotes.model.Note
import kotlinx.coroutines.delay

class NotesRepository {

    suspend fun getNotes(): List<Note> {
        delay(2000)
        return listOf(note1, note2, note3)
    }
}

private val note1 = Note(1L, "Note1", "This is note one")
private val note2 = Note(2L, "Note2", "This is note two")
private val note3 = Note(3L, "Note3", "This is note three") 
