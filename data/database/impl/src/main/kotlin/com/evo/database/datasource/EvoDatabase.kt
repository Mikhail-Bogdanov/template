package com.evo.database.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evo.database.datasource.entity.SampleEntity
import com.evo.database.datasource.util.DatabaseUtils.DATABASE_VERSION

@Database(
    entities = [
        SampleEntity::class,
    ],
    version = DATABASE_VERSION,
)
internal abstract class EvoDatabase : RoomDatabase() {

}
