package com.app.wawacred_grupo2.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "iron_doses")
data class IronDose(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val doseTitle: String,
    val supplementType: String,
    val amount: String,
    val date: String,
    val time: String,
    val notes: String
)
