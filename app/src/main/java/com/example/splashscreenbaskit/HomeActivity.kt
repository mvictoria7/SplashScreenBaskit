package com.example.splashscreenbaskit

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Preview (showBackground = true)
@Composable
fun HomeActivity() {
    HomeActivity(navController = rememberNavController())
}
@Composable
fun HomeActivity(navController: NavController) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color (color = 0xFFf0f0f0)
    ) {    }

    Text(
        text = "Home Screen",
        fontSize = 50.sp
    )

}