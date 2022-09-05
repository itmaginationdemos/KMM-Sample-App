package com.example.voicenotes.android.main.di

import com.example.voicenotes.android.main.MainViewModel
import com.example.voicenotes.android.main.notes.detail.NotesDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { NotesDetailViewModel(get()) }
}
