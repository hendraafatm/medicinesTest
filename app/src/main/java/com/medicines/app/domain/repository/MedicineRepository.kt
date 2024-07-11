package com.medicines.app.domain.repository

import com.medicines.app.domain.entity.Medicine


interface MedicineRepository {
    suspend fun getMedicines(): List<Medicine>
}