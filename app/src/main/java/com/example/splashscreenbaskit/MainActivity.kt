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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.splashscreenbaskit.AccountDetails.AccountActivity
import com.example.splashscreenbaskit.AccountDetails.NotificationsActivity
import com.example.splashscreenbaskit.Carts.CartScreen
import com.example.splashscreenbaskit.LoginSignup.LoginActivity
import com.example.splashscreenbaskit.LoginSignup.OnboardingScreen
import com.example.splashscreenbaskit.LoginSignup.SignUpActivity
import com.example.splashscreenbaskit.Products.AppleScreen
import com.example.splashscreenbaskit.Products.OrangeScreen
import com.example.splashscreenbaskit.ui.theme.SplashScreenBaskitTheme
import com.example.splashscreenbaskit.viewmodel.CartViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("NewApi", "ComposableDestinationInComposeScope")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        setContent {
            SplashScreenBaskitTheme {
                val navController = rememberNavController()

                // Initialize CartViewModel here
                val cartViewModel: CartViewModel = viewModel()

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
                        // Pass CartViewModel to AppleScreen
                        AppleScreen(navController = navController, cartViewModel = cartViewModel)
                    }
                    composable("OrangeScreen") {
                        OrangeScreen(navController = navController)
                    }
                    composable("CartScreen") {
                        // Pass CartViewModel to CartScreen
                        CartScreen(cartViewModel = cartViewModel,)
                    }
                    composable("NotificationsActivity") {
                        NotificationsActivity(navController)
                    }
                }
            }
        }
    }
}
