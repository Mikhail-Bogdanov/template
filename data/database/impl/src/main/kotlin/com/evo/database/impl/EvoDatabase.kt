package com.evo.database.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evo.database.impl.entity.EvoEntity
import com.evo.database.impl.util.DatabaseUtils.DATABASE_VERSION

@Database(
    entities = [
        EvoEntity::class
    ],
    version = DATABASE_VERSION,
)
abstract class EvoDatabase : RoomDatabase() {

}