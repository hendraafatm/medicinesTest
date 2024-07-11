package com.medicines.app.ui.medicine

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.medicines.app.ui.medicine.viewmodel.MedicineViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.*

@Composable
fun MedicineListScreen(navController: NavController, viewModel: MedicineViewModel = hiltViewModel()) {
    val medicines by viewModel.medicines.collectAsState()

    // Trigger fetching of medicines on first composition
    LaunchedEffect(Unit) {
        viewModel.fetchMedicines()
    }

    LazyColumn {
        items(medicines) { medicine ->
            MedicineCard(medicine) {
                navController.navigate("medicineDetail/${medicine.name}/${medicine.dose}/${medicine.strength}")
            }
        }
    }
}
