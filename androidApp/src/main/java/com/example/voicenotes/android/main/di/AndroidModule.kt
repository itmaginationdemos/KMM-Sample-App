package com.example.voicenotes.android.main.di

import com.example.voicenotes.android.main.core.di.coreModule

fun androidModule() = listOf(
    viewModelModule,
    coreModule
)
