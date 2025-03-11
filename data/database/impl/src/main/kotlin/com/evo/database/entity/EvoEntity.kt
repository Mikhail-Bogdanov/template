package com.evo.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EvoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int
)