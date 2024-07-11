package com.medicines.app.domain.usecase

import com.medicines.app.domain.entity.Medicine
import com.medicines.app.domain.repository.MedicineRepository
import javax.inject.Inject

class GetMedicinesUseCase @Inject constructor(
    private val repository: MedicineRepository
) {
    suspend fun execute(): List<Medicine> {
        return repository.getMedicines()
    }
}