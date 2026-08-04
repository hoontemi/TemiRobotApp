package com.ibtech.temirobotapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ibtech.temirobotapp.ui.LibraryViewModel
import com.ibtech.temirobotapp.ui.admin.AdminScreen
import com.ibtech.temirobotapp.ui.children.ChildrenMenuScreen
import com.ibtech.temirobotapp.ui.event.EventDetailScreen
import com.ibtech.temirobotapp.ui.event.EventListScreen
import com.ibtech.temirobotapp.ui.facility.FacilityDetailScreen
import com.ibtech.temirobotapp.ui.facility.FacilityListScreen
import com.ibtech.temirobotapp.ui.facility.LocationGuideScreen
import com.ibtech.temirobotapp.ui.facility.NavigationPrepareScreen
import com.ibtech.temirobotapp.ui.home.HomeScreen
import com.ibtech.temirobotapp.ui.usage.UsageCategoryScreen
import com.ibtech.temirobotapp.ui.usage.UsageDetailScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val libraryViewModel: LibraryViewModel = viewModel()

    fun goHome() {
        navController.navigate(AppRoutes.HOME) {
            popUpTo(AppRoutes.HOME) { inclusive = true }
            launchSingleTop = true
        }
    }

    NavHost(
        navController = navController,
        startDestination = AppRoutes.HOME
    ) {
        composable(AppRoutes.HOME) {
            HomeScreen(
                libraryViewModel = libraryViewModel,
                onAdminClick = { navController.navigate(AppRoutes.ADMIN) },
                onFacilityClick = { navController.navigate(AppRoutes.FACILITY_LIST) },
                onUsageClick = { navController.navigate(AppRoutes.USAGE_CATEGORY) },
                onChildrenClick = { navController.navigate(AppRoutes.CHILDREN_MENU) },
                onEventClick = { navController.navigate(AppRoutes.EVENT_LIST) }
            )
        }

        composable(AppRoutes.ADMIN) {
            AdminScreen(
                libraryViewModel = libraryViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(AppRoutes.FACILITY_LIST) {
            FacilityListScreen(
                onFacilityClick = { id -> navController.navigate(AppRoutes.facilityDetail(id)) },
                onBackClick = { navController.popBackStack() },
                onHomeClick = { goHome() }
            )
        }
        composable(AppRoutes.FACILITY_DETAIL) { backStackEntry ->
            val facilityId = backStackEntry.arguments?.getString(AppRoutes.ARG_FACILITY_ID).orEmpty()
            FacilityDetailScreen(
                facilityId = facilityId,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { goHome() },
                onEscortClick = { id -> navController.navigate(AppRoutes.navigationPrepare(id)) },
                onLocationOnlyClick = { id -> navController.navigate(AppRoutes.locationGuide(id)) }
            )
        }
        composable(AppRoutes.NAVIGATION_PREPARE) { backStackEntry ->
            val facilityId = backStackEntry.arguments?.getString(AppRoutes.ARG_FACILITY_ID).orEmpty()
            NavigationPrepareScreen(
                facilityId = facilityId,
                onCancelClick = { navController.popBackStack() },
                onBackClick = { navController.popBackStack() },
                onHomeClick = { goHome() }
            )
        }
        composable(AppRoutes.LOCATION_GUIDE) { backStackEntry ->
            val facilityId = backStackEntry.arguments?.getString(AppRoutes.ARG_FACILITY_ID).orEmpty()
            LocationGuideScreen(
                facilityId = facilityId,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { goHome() }
            )
        }

        composable(AppRoutes.USAGE_CATEGORY) {
            UsageCategoryScreen(
                onCategoryClick = { id -> navController.navigate(AppRoutes.usageDetail(id)) },
                onBackClick = { navController.popBackStack() },
                onHomeClick = { goHome() }
            )
        }
        composable(AppRoutes.USAGE_DETAIL) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString(AppRoutes.ARG_CATEGORY_ID).orEmpty()
            UsageDetailScreen(
                categoryId = categoryId,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { goHome() }
            )
        }

        composable(AppRoutes.CHILDREN_MENU) {
            ChildrenMenuScreen(
                onBackClick = { navController.popBackStack() },
                onHomeClick = { goHome() }
            )
        }

        composable(AppRoutes.EVENT_LIST) {
            EventListScreen(
                onEventClick = { id -> navController.navigate(AppRoutes.eventDetail(id)) },
                onBackClick = { navController.popBackStack() },
                onHomeClick = { goHome() }
            )
        }
        composable(AppRoutes.EVENT_DETAIL) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString(AppRoutes.ARG_EVENT_ID).orEmpty()
            EventDetailScreen(
                eventId = eventId,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { goHome() }
            )
        }
    }
}
