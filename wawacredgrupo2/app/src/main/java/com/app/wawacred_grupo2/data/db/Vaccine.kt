package com.app.wawacred_grupo2.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vaccines")
data class Vaccine(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val dose: String,
    val protectsAgainst: String,
    val date: String,
    val time: String
)
