package com.example.voicenotes.android.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voicenotes.model.Note
import com.example.voicenotes.usecase.GetNotes
import kotlinx.coroutines.launch

class MainViewModel(
    private val getNotes: GetNotes
) : ViewModel() {

    val notes = mutableStateOf<List<Note>>(emptyList())

    init {
        viewModelScope.launch {
            notes.value = getNotes()
        }
    }
}
