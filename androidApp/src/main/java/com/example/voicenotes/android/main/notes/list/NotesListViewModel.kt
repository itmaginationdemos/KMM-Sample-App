package com.example.voicenotes.android.main.notes.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voicenotes.model.Note
import com.example.voicenotes.usecase.DeleteNote
import com.example.voicenotes.usecase.StreamNotes
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NotesListViewModel(
    private val streamNotes: StreamNotes,
    private val deleteNote: DeleteNote
) : ViewModel() {

    val notes = mutableStateOf<List<Note>>(emptyList())

    init {
        viewModelScope.launch {
            streamNotes().collect {
                notes.value = it
            }
        }
    }

    fun onDeleteClicked(id: String) {
        viewModelScope.launch { deleteNote(id) }
    }
}
