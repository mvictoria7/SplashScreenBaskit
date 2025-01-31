package com.example.splashscreenbaskit

import HomeScreen
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                        AppleScreen(navController)  // ✅ Pass navController correctly
                    }
                    composable(
                        "add_to_cart/{weight}/{quantity}/{totalPrice}",
                        arguments = listOf(
                            navArgument("weight") { type = NavType.StringType },
                            navArgument("quantity") { type = NavType.IntType },
                            navArgument("totalPrice") { type = NavType.FloatType }
                        )
                    ) { backStackEntry ->
                        val weight = backStackEntry.arguments?.getString("weight") ?: "1 pc"
                        val quantity = backStackEntry.arguments?.getInt("quantity") ?: 1
                        val totalPrice = backStackEntry.arguments?.getFloat("totalPrice") ?: 0f

                        AddToCartScreen(
                            navController = navController,
                            productName = "Apple",
                            quantity = quantity,
                            price = totalPrice.toDouble()
                        )
                    }
                }
            }
        }
    }
}
