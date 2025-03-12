package com.evo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evo.database.entity.EvoEntity
import com.evo.database.util.DatabaseUtils.DATABASE_VERSION

@Database(
    entities = [
        EvoEntity::class
    ],
    version = DATABASE_VERSION,
)
abstract class EvoDatabase : RoomDatabase() {

}