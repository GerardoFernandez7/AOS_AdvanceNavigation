package com.gerardo_fdez.advancenavigationlab7

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.google.gson.Gson

// Definición de la clase de datos
data class UserData(
    val name: String,
    val age: Int,
    val idNumber: String,
    val driverLicenseNumber: String,
    val socialSecurityNumber: String
)

// Definición de las pantallas de navegación
sealed class Screen(val route: String) {
    object EnterData : Screen("enter_data")
    object DisplayData : Screen("display_data/{userData}") {
        fun createRoute(userData: UserData): String {
            val gson = Gson()
            val userDataJson = gson.toJson(userData)
            return "display_data/${Uri.encode(userDataJson)}"
        }
    }
}

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.EnterData.route,
        modifier = modifier
    ) {
        composable(Screen.EnterData.route) {
            EnterData(
                onSendData = { userData ->
                    navController.navigate(Screen.DisplayData.createRoute(userData))
                }
            )
        }
        composable(
            route = Screen.DisplayData.route,
            arguments = listOf(
                navArgument("userData") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userDataJson = backStackEntry.arguments?.getString("userData")
            val gson = Gson()
            val userData = gson.fromJson(userDataJson, UserData::class.java)
            DisplayData(userData = userData)
        }
    }
}
