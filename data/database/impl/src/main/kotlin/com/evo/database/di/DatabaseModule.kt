package com.evo.database.di

import androidx.room.Room
import com.evo.database.datasource.EvoDatabase
import com.evo.database.datasource.util.DatabaseUtils.DATABASE_NAME
import com.evo.di.EvoModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module

class DatabaseModule : EvoModule {

    override fun Module.initialize() {
        single {
            Room.databaseBuilder(
                androidContext(),
                EvoDatabase::class.java,
                DATABASE_NAME,
            ).build()
        }
    }
}
