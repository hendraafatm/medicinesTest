package com.medicines.app.domain.usecase

import com.medicines.app.domain.entity.Medicine
import com.medicines.app.domain.repository.MedicineRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class GetMedicinesUseCaseTest {

    @Mock
    private lateinit var repository: MedicineRepository

    private lateinit var useCase: GetMedicinesUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetMedicinesUseCase(repository)
    }

    @Test
    fun `test execute returns medicine list`() = runTest {
        val medicineList = listOf(Medicine(1, "Medicine1", "Dose1", "Strength1"))

        `when`(repository.getMedicines()).thenReturn(medicineList)

        val result = useCase.execute()

        assert(result == medicineList)
    }
}
