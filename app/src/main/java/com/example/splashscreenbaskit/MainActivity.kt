package com.example.splashscreenbaskit

import HomeScreen
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
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


                    }
                    }
                }
            }
        }



