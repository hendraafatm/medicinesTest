package com.medicines.app.ui.viewmodel

import com.medicines.app.domain.entity.Medicine
import com.medicines.app.domain.usecase.GetMedicinesUseCase
import com.medicines.app.ui.medicine.viewmodel.MedicineViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MedicineViewModelTest {

    @Mock
    private lateinit var getMedicinesUseCase: GetMedicinesUseCase

    private lateinit var viewModel: MedicineViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = MedicineViewModel(getMedicinesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the Main dispatcher to the original Main dispatcher
        testDispatcher.cancel()
    }

    @Test
    fun `test fetchMedicines updates medicines`() = runTest {
        val medicineList = listOf(Medicine(1, "Medicine1", "Dose1", "Strength1"))
        `when`(getMedicinesUseCase.execute()).thenReturn(medicineList)

        viewModel.fetchMedicines()

        testDispatcher.scheduler.advanceUntilIdle() // Advances the virtual clock of the dispatcher until all tasks are executed

        val result = viewModel.medicines.first() // Collects the first emitted value from the StateFlow

        assert(result == medicineList)
    }
}
