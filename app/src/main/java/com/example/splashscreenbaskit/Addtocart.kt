package com.example.splashscreenbaskit

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun AddToCartScreen(
    navController: NavController,
    productName: String,
    quantity: Int,
    price: Double
) {
    val context = LocalContext.current  // ✅ Fix Toast context issue'


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Text(
            text = "Your Cart",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Product Details
        Text(
            text = "Product: $productName",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Quantity: $quantity",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "Total Price: ₱ ${"%.2f".format(price)}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Back Button
        Button(
            onClick = {
                navController.popBackStack()  // ✅ Navigate back
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
        ) {
            Text(
                text = "Go Back",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Proceed to Checkout Button
        Button(
            onClick = {
                Toast.makeText(context, "Proceeding to Checkout...", Toast.LENGTH_SHORT).show()
                // ✅ Navigate to checkout screen (implement navigation if needed)
                // navController.navigate("checkoutScreen")
            },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D7151))
        ) {
            Text(
                text = "Proceed to Checkout",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}
