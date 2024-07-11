package com.medicines.app.data.repository


import com.medicines.app.data.local.MedicineDao
import com.medicines.app.data.model.DataMedicine
import com.medicines.app.data.remote.ApiService
import com.medicines.app.domain.entity.Medicine
import com.medicines.app.domain.mapper.MedicineMapper
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MedicineRepositoryImplTest {

    @Mock
    private lateinit var medicineDao: MedicineDao

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var mapper: MedicineMapper

    private lateinit var repository: MedicineRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = MedicineRepositoryImpl(medicineDao, apiService, mapper)
    }

    @Test
    fun `test getMedicines success from API`() = runTest {
        val dataMedicineList = listOf(DataMedicine(1, "Medicine1", "Dose1", "Strength1"))
        val domainMedicineList = listOf(Medicine(1, "Medicine1", "Dose1", "Strength1"))

        `when`(apiService.getMedicines()).thenReturn(dataMedicineList)
        `when`(mapper.mapToDomainModel(dataMedicineList[0])).thenReturn(domainMedicineList[0])
        `when`(mapper.mapToDataModel(domainMedicineList[0])).thenReturn(dataMedicineList[0])

        val result = repository.getMedicines()

        assertEquals(domainMedicineList, result)
        verify(medicineDao).insertMedicines(dataMedicineList)
    }

    @Test
    fun `test getMedicines fallback to local database`() = runTest {
        val dataMedicineList = listOf(DataMedicine(1, "Medicine1", "Dose1", "Strength1"))
        val domainMedicineList = listOf(Medicine(1, "Medicine1", "Dose1", "Strength1"))

        `when`(apiService.getMedicines()).thenThrow(RuntimeException())
        `when`(medicineDao.getMedicines()).thenReturn(dataMedicineList)
        `when`(mapper.mapToDomainModel(dataMedicineList[0])).thenReturn(domainMedicineList[0])

        val result = repository.getMedicines()

        assertEquals(domainMedicineList, result)
    }
}
