package com.medicines.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.medicines.app.data.model.DataMedicine

@Dao
interface MedicineDao {
    @Insert
    suspend fun insertMedicines(medicines: List<DataMedicine>)

    @Query("SELECT * FROM medicines")
    suspend fun getMedicines(): List<DataMedicine>
}
