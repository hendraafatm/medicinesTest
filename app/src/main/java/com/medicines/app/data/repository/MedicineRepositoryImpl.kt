package com.medicines.app.data.repository

import com.medicines.app.data.local.MedicineDao
import com.medicines.app.data.remote.ApiService
import com.medicines.app.domain.entity.Medicine
import com.medicines.app.domain.mapper.MedicineMapper
import com.medicines.app.domain.repository.MedicineRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MedicineRepositoryImpl @Inject constructor(
    private val medicineDao: MedicineDao,
    private val apiService: ApiService,
    private val mapper: MedicineMapper
) : MedicineRepository {

    override suspend fun getMedicines(): List<Medicine> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getMedicines().map { mapper.mapToDomainModel(it) }
                medicineDao.insertMedicines(response.map { mapper.mapToDataModel(it) })
                response
            } catch (e: Exception) {
                medicineDao.getMedicines().map { mapper.mapToDomainModel(it) }
            }
        }
    }
}