
package com.example.splashscreenbaskit

import HomeScreen
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.design.loginregister.LoginActivity
import com.example.splashscreenbaskit.ui.theme.SplashScreenBaskitTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("NewApi", "ComposableDestinationInComposeScope")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        setContent {
            SplashScreenBaskitTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "OnBoardingScreen") {
                    composable("OnBoardingScreen") {
                        OnboardingScreen(navController)
                    }
                    composable("LoginActivity") {
                        LoginActivity(navController)
                    }
                    composable("SignUpActivity") {
                        SignUpActivity(navController)
                    }
                    composable("AccountActivity") {
                        AccountActivity(navController)
                    }
                    composable("HomeActivity") {
                        HomeScreen()
                    }
                    composable("AppleScreen") {
                        AppleScreen(navController)
                    }
                    composable("OrangeScreen") {
                        OrangeScreen(navController)
                    }
                    composable("AddToCart") {
                    }
                    composable("NotificationsActivity") {
                        NotificationsActivity(navController)
                    }
                    composable("SettingsActivity") {
                        SettingsActivity(navController)
                    }

                    composable("CartApple/{weight}/{quantity}/{totalPrice}") { backStackEntry ->
                        val weight = backStackEntry.arguments?.getString("weight") ?: "1 pc"
                        val quantity =
                            backStackEntry.arguments?.getString("quantity")?.toIntOrNull() ?: 0
                        val totalPrice =
                            backStackEntry.arguments?.getString("totalPrice")?.toDoubleOrNull()
                                ?: 0.0

                        composable("CartOrange/{weight}/{quantity}/{totalPrice}") { backStackEntry ->
                            val weight = backStackEntry.arguments?.getString("weight") ?: "1 pc"
                            val quantity =
                                backStackEntry.arguments?.getString("quantity")?.toIntOrNull() ?: 0
                            val totalPrice =
                                backStackEntry.arguments?.getString("totalPrice")?.toDoubleOrNull()
                                    ?: 0.0


                            CartAppleScreen(
                                navController = navController,
                                selectedWeight = weight,
                                quantity = quantity,
                                totalPrice = totalPrice
                            )

                            CartOrangeScreen(
                                navController = navController,
                                selectedWeight = weight,
                                quantity = quantity,
                                totalPrice = totalPrice

                            )
                        }

                    }
                }
            }
        }
    }
}
