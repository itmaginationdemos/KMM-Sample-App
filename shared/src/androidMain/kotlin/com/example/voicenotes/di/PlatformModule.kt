package com.example.voicenotes.di

import com.example.voicenotes.kmm.shared.cache.AppDatabase
import com.example.voicenotes.kmm.shared.cache.Database
import com.squareup.sqldelight.android.AndroidSqliteDriver
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        Database(
            AndroidSqliteDriver(AppDatabase.Schema, get(), "test.db")
        )
    }
}
