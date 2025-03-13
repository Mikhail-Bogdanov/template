package com.evo.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("evo_entity")
internal data class EvoEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int
)