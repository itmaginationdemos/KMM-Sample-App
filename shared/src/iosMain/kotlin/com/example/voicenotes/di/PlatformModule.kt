import org.koin.core.module.Module

import org.koin.dsl.module

actual fun platformModule() = module {
    Database(
        NativeSqliteDriver(AppDatabase.Schema, "test.db"),
    )
}
