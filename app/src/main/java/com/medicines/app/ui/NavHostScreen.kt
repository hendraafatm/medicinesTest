package com.medicines.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.medicines.app.ui.greeting.GreetingScreen
import com.medicines.app.ui.login.LoginScreen
import com.medicines.app.ui.medicine.MedicineDetailScreen

@Composable
fun NavHostScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("greeting/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username")
            if (username != null) {
                GreetingScreen(username, navController)
            }
        }
        composable("medicineDetail/{name}/{dose}/{strength}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")
            val dose = backStackEntry.arguments?.getString("dose")
            val strength = backStackEntry.arguments?.getString("strength")
            if (name != null && dose != null && strength != null) {
                MedicineDetailScreen(name, dose, strength)
            }
        }
    }
}
