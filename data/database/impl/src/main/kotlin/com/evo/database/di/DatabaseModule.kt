package com.evo.database.di

import androidx.room.Room
import com.evo.database.datasource.EvoDatabase
import com.evo.database.datasource.util.DatabaseUtils.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DatabaseModule {
    private val module = module {
        single {
            Room.databaseBuilder(
                androidContext(),
                EvoDatabase::class.java,
                DATABASE_NAME,
            ).build()
        }
    }

    operator fun invoke() = module
}
