package com.example.voicenotes.android.main.notes.newnote

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voicenotes.android.main.core.navigation.NavEvent
import com.example.voicenotes.android.main.core.navigation.Navigator
import com.example.voicenotes.usecase.GenerateNote
import kotlinx.coroutines.launch
import mutate

class NewNoteViewModel(
    private val generateNote: GenerateNote,
    private val navigator: Navigator
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
            is NewNoteEvent.OnSave -> {
                viewModelScope.launch {
                    generateNote(
                        title = state.value.title,
                        content = state.value.content,
                        filePath = event.filePath
                    )
                    navigator.emitDestinationSync(NavEvent.CloseNewNote)
                }
            }
        }
    }
}
