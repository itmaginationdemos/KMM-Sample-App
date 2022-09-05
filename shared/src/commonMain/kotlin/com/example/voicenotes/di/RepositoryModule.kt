package com.example.voicenotes.di

import com.example.voicenotes.repository.NotesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::NotesRepository)
}
