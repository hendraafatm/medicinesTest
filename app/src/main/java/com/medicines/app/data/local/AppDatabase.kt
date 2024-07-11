package com.medicines.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.medicines.app.data.model.DataMedicine

@Database(entities = [DataMedicine::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao
}