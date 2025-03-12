package com.evo.database.di

import androidx.room.Room
import com.evo.database.EvoDatabase
import com.evo.database.util.DatabaseUtils.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DatabaseModule {
    private val module = module {
        single {
            val evoDatabase = Room.databaseBuilder(
                androidContext(),
                EvoDatabase::class.java,
                DATABASE_NAME,
            )

            evoDatabase.build()
        }
    }

    operator fun invoke() = module
}