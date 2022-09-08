package com.example.voicenotes.di

fun appModule() = listOf(
    useCaseModule,
    repositoryModule,
    platformModule()
)
