package com.example.splashscreenbaskit

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true)
@Composable
fun TermsActivity() {
    TermsActivity(navController = rememberNavController())
}

@Composable
fun TermsActivity(navController: NavController) {

}