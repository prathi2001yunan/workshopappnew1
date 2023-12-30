package com.android.roomdbtest.presentation.navigation

sealed class Screens(val route: String) {
    object SplashScreen : Screens("splash_screen")
    object LoginScreen : Screens("login_screen")
    object  RegisterScreen : Screens("register_screen")

    object DashBoardScreen : Screens("dashboard_screen")

    object ProfileScreen : Screens("profile_screen")

    object ProfileEditScreen : Screens("profile_edit_screen")

    object FinanceEditScreen : Screens("finance_edit_screen")
}