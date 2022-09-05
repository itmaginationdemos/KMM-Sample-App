package com.example.voicenotes.di

import com.example.voicenotes.usecase.GetNotes
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GetNotes)
}