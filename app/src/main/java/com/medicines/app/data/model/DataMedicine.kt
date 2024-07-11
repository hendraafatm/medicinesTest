package com.medicines.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines")
data class DataMedicine(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val dose: String,
    val strength: String
)

