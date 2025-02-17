package com.example.splashscreenbaskit.Carts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.splashscreenbaskit.viewmodel.CartViewModel


@Composable
fun CartApple(navController: NavHostController) {
    CartApple(navController = rememberNavController())
}
@Composable
fun CartAppleScreen(
    navController: NavController,
    selectedWeight: String,
    quantity: Int,
    totalPrice: Double
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {4
        Text(text = "Selected Weight: $selectedWeight")
        Text(text = "Quantity: $quantity")
        Text(text = "Total Price: ₱${"%.2f".format(totalPrice)}")

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Weight: $selectedWeight, Quantity: $quantity, Total Price: ₱${"%.2f".format(totalPrice)}")

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Go Back")
        }
    }
}