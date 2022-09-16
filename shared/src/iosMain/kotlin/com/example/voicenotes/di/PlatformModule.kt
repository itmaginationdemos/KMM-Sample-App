package com.example.voicenotes.di

import com.example.voicenotes.kmm.shared.cache.AppDatabase
import com.example.voicenotes.kmm.shared.cache.Database
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.dsl.module

actual fun platformModule() = module {
    Database(
        NativeSqliteDriver(AppDatabase.Schema, "test.db")
    )
}
