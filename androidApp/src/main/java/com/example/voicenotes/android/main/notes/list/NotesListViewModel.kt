package com.example.voicenotes.android.main.notes.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voicenotes.model.Note
import com.example.voicenotes.usecase.DeleteNote
import com.example.voicenotes.usecase.GetNotes
import kotlinx.coroutines.launch

class NotesListViewModel(
    private val getNotes: GetNotes,
    private val deleteNote: DeleteNote
) : ViewModel() {

    val notes = mutableStateOf<List<Note>>(emptyList())

    init {
        viewModelScope.launch {
            notes.value = getNotes()
        }
    }

    fun onDeleteClicked(id: String) {
        viewModelScope.launch {
            notes.value = deleteNote(id)
        }
    }
}
