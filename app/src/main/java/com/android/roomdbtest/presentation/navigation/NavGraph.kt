package com.android.roomdbtest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.roomdbtest.presentation.screens.DashBoardScreen
import com.android.roomdbtest.presentation.screens.LoginScreen
import com.android.roomdbtest.presentation.screens.ProfileScreen
import com.android.roomdbtest.presentation.screens.RegisterScreen
import com.android.roomdbtest.presentation.screens.SplashScreen
import com.android.roomdbtest.presentation.viewmodels.LoginViewModel
import com.android.roomdbtest.presentation.viewmodels.ProfileViewModel
import com.android.roomdbtest.presentation.viewmodels.RegisterViewModel

@Composable
fun NavGraph(navController: NavHostController = rememberNavController(),
             noteViewModel: RegisterViewModel,
             loginViewModel: LoginViewModel,
             profileViewModel: ProfileViewModel) {
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(route = Screens.SplashScreen.route) {
            SplashScreen {
                navController.navigate(Screens.LoginScreen.route) {
                    popUpTo(Screens.SplashScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
        composable(route = Screens.LoginScreen.route) {
            LoginScreen(
                loginViewModel,
                context = LocalContext.current,(
                {
                navController.navigate(Screens.DashBoardScreen.route) {
                    navController.popBackStack(Screens.LoginScreen.route, true)
                }
            }),({ navController.navigate(Screens.RegisterScreen.route)}))
        }
            composable(route = Screens.RegisterScreen.route) {
               RegisterScreen(registerViewModel = noteViewModel) {
                   navController.navigate(Screens.LoginScreen.route) {
                       navController.popBackStack(Screens.RegisterScreen.route, true)
                   }
                }
            }
        composable(route = Screens.DashBoardScreen.route) {
            DashBoardScreen({
                navController.navigate(Screens.LoginScreen.route) {
                    navController.popBackStack(Screens.DashBoardScreen.route, true)
                }
            }, ({ navController.navigate(Screens.ProfileScreen.route) }))
        }
        composable(route = Screens.ProfileScreen.route) {
            ProfileScreen(profileViewModel, registerViewModel = noteViewModel, {
                navController.navigate(Screens.ProfileEditScreen.route)
            }, {
                navController.navigate(Screens.FinanceEditScreen.route)
            })
        }

    }
}
