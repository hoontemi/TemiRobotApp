package com.ibtech.temirobotapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ibtech.temirobotapp.ui.LibraryViewModel
import com.ibtech.temirobotapp.ui.admin.AdminScreen
import com.ibtech.temirobotapp.ui.facility.FacilityDetailScreen
import com.ibtech.temirobotapp.ui.facility.FacilityListScreen
import com.ibtech.temirobotapp.ui.home.HomeScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val libraryViewModel: LibraryViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.HOME
    ) {
        composable(AppRoutes.HOME) {
            HomeScreen(
                libraryViewModel = libraryViewModel,
                onAdminClick = { navController.navigate(AppRoutes.ADMIN) }
            )
        }
        composable(AppRoutes.ADMIN) {
            AdminScreen(
                libraryViewModel = libraryViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(AppRoutes.FACILITY_LIST) {
            FacilityListScreen()
        }
        composable(AppRoutes.FACILITY_DETAIL) {
            FacilityDetailScreen()
        }
    }
}
