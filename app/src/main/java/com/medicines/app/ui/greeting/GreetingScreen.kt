package com.medicines.app.ui.greeting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.medicines.app.ui.medicine.MedicineListScreen
import java.util.Calendar

@Composable
fun GreetingScreen(username: String, navController: NavController) {
    val greeting = remember {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        when {
            hour in 0..11 -> "Good Morning"
            hour in 12..15 -> "Good Afternoon"
            hour in 16..20 -> "Good Evening"
            else -> "Good Night"
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "$greeting $username")
        MedicineListScreen(navController)
    }
}
