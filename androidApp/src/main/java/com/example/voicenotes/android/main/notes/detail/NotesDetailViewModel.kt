package com.example.voicenotes.android.main.notes.detail

import androidx.lifecycle.ViewModel
import com.example.voicenotes.usecase.GetNote

class NotesDetailViewModel(
    private val getNote: GetNote
) : ViewModel() {

    fun getNoteWith(id: String?) = getNote(id)
}
