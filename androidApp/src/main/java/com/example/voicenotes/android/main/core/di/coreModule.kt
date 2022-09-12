package com.example.voicenotes.android.main.core.di

import com.example.voicenotes.android.main.core.navigation.Navigator
import org.koin.dsl.module

val coreModule = module {
    single { Navigator() }
}
