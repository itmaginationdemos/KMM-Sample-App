package com.example.voicenotes.di

import com.example.voicenotes.repository.NotesRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val repositoryModule = module {
    factoryOf(::NotesRepository)
}
