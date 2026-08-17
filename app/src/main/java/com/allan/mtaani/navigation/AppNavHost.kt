package com.allan.mtaani.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.allan.mtaani.ui.screens.authentication.LoginScreen
import com.allan.mtaani.ui.screens.authentication.RegisterScreen
import com.allan.mtaani.ui.screens.forgotpassword.ForgotPasswordScreen

import com.allan.mtaani.ui.screens.home.HomeScreen
import com.allan.mtaani.ui.screens.onboading.onboadingScreen1
import com.allan.mtaani.ui.screens.onboading.onboadingScreen2
import com.allan.mtaani.ui.screens.onboading.onboadingScreen3
import com.allan.mtaani.ui.screens.report.ReportScreen
import com.allan.mtaani.ui.screens.splash.SplashScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_ONBOADING1) {
            onboadingScreen1(navController)
        }

        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUT_ONBOADING2) {
            onboadingScreen2(navController)
        }

        composable(ROUT_ONBOADING3) {
            onboadingScreen3(navController)
        }

        composable(ROUT_HOME) {
            HomeScreen(navController)
        }

        composable(ROUT_LOGIN) {
            LoginScreen(navController)
        }

        composable(ROUT_REGISTER) {
            RegisterScreen(navController)
        }

        composable(ROUT_REPORT) {
            ReportScreen(navController)
        }

        composable(ROUT_FORGOTPASSWORD) {
            ForgotPasswordScreen(navController)
        }



    }
}