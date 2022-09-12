package com.example.voicenotes.di

import com.example.voicenotes.usecase.DeleteNote
import com.example.voicenotes.usecase.GenerateNote
import com.example.voicenotes.usecase.GetNote
import com.example.voicenotes.usecase.StreamNotes
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GetNote)
    factoryOf(::GenerateNote)
    factoryOf(::DeleteNote)
    factoryOf(::StreamNotes)
}
