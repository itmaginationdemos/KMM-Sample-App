package com.example.voicenotes.android.main.notes.newnote

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voicenotes.android.main.MainViewModel
import com.example.voicenotes.usecase.GenerateNote
import kotlinx.coroutines.launch
import mutate

class NewNoteViewModel(
    private val generateNote: GenerateNote,
    private val vm: MainViewModel
) : ViewModel() {

    val state = mutableStateOf(NewFormState())

    fun onEvent(event: NewNoteEvent) {
        when (event) {
            is NewNoteEvent.OnTitleUpdate -> state.mutate {
                copy(
                    title = event.title,
                    enabled = event.title.isNotEmpty() && this.content.isNotEmpty()
                )
            }
            is NewNoteEvent.OnContentUpdate -> state.mutate {
                copy(
                    content = event.content,
                    enabled = event.content.isNotEmpty() && this.title.isNotEmpty()
                )
            }
            NewNoteEvent.OnSave -> {
                viewModelScope.launch {
                    generateNote(
                        state.value.title,
                        state.value.content
                    )
                    vm.closeAddNote()
                }
            }
        }
    }
}
