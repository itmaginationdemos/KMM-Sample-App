package com.example.voicenotes.android.main.di

import com.example.voicenotes.android.main.MainViewModel
import com.example.voicenotes.android.main.notes.detail.NotesDetailViewModel
import com.example.voicenotes.android.main.notes.list.NotesListViewModel
import com.example.voicenotes.android.main.notes.newnote.NewNoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::NotesListViewModel)
    viewModelOf(::NotesDetailViewModel)
    viewModelOf(::NewNoteViewModel)
    viewModelOf(::MainViewModel)
}
