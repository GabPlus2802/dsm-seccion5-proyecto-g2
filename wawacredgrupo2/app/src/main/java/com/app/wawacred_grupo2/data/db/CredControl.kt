package com.app.wawacred_grupo2.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cred_controls")
data class CredControl(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val date: String,
    val time: String,
    val weight: String,
    val height: String,
    val headCircumference: String,
    val controlType: String,
    val observations: String
)
