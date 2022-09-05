package com.example.voicenotes.di

import com.example.voicenotes.usecase.GetNotes
import com.example.voicenotes.usecase.GetNote
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GetNotes)
    factoryOf(::GetNote)
}
