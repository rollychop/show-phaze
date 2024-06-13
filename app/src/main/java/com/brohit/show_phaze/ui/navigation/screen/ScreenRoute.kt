package com.brohit.show_phaze.ui.navigation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenRoute(
    val route: String,
) {
    data object Loading : ScreenRoute("loading")
    data object Auth : ScreenRoute("auth-route")
    data object Authenticated : ScreenRoute("authenticated-route")
}

sealed class AuthRoute(route: String) : ScreenRoute(route) {
    data object Login : AuthRoute("login")
    data object Otp : AuthRoute("otp")
    data object Signup : AuthRoute("signup")
    data object ForgetPassword : AuthRoute("forget-password")
}

sealed class AuthenticatedRoute(route: String) : ScreenRoute(route) {
    data object Home : AuthenticatedRoute("home") {
        data object Dashboard : BottomBarRoute("dashboard", "Dashboard", Icons.Default.Dashboard)
        data object Profile : BottomBarRoute("profile", "Profile", Icons.Default.Person)
        data object Setting : BottomBarRoute("setting", "Setting", Icons.Default.Settings)
    }
}

sealed class NavigationRoute(route: String, val name: String, val icon: ImageVector) :
    AuthenticatedRoute(route)

sealed class BottomBarRoute(route: String, name: String, icon: ImageVector) :
    NavigationRoute(route, name, icon)


val BottomBarRoutes: List<BottomBarRoute> = listOf(
    AuthenticatedRoute.Home.Dashboard,
    AuthenticatedRoute.Home.Profile,
    AuthenticatedRoute.Home.Setting,
)