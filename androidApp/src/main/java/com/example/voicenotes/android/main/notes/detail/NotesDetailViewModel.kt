package com.example.voicenotes.android.main.notes.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voicenotes.model.Note
import com.example.voicenotes.usecase.GetNote
import kotlinx.coroutines.launch

class NotesDetailViewModel(
    private val getNote: GetNote
) : ViewModel() {

    val note: MutableState<Note?> = mutableStateOf(null)

    fun getNoteWith(id: String?) {
        viewModelScope.launch {
            note.value = getNote(id)
        }
    }
}
