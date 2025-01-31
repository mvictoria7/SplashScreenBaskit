package com.example.splashscreenbaskit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun AppleScreen() {
    var quantity by remember { mutableStateOf(1) }
    var selectedWeight by remember { mutableStateOf("1 pc") }
    val basePrice = 32.25
    val priceIncrease = 30.0

    val priceForWeight = when (selectedWeight) {
        "1 pc" -> basePrice
        "1/4 kg" -> basePrice + priceIncrease
        "1/2 kg" -> basePrice + priceIncrease
        "1 kg" -> basePrice + priceIncrease * 2
        else -> basePrice
    }
    val totalPrice = priceForWeight * quantity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Image section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.apple),
                contentDescription = "Apple",
                modifier = Modifier.fillMaxSize()
            )
            IconButton(
                onClick = { /* Handle back action */ },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopStart)
                    .background(Color.White, shape = RoundedCornerShape(50))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = "Apple",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                repeat(4) {
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "Star",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(25.dp)
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.star_outline),
                    contentDescription = "Star Outline",
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
            }

            Text(
                text = "₱ ${"%.2f".format(priceForWeight)}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Quantity selection
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Product Description",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { if (quantity > 1) quantity-- }) {
                        Icon(
                            painter = painterResource(id = R.drawable.minus),
                            contentDescription = "Minus",
                            tint = Color.Black,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Text(
                        text = "$quantity",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    IconButton(onClick = { quantity++ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = "Add",
                            tint = Color.Black,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Fresh apples, perfect for a healthy snack!",
                fontSize = 18.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Weight options
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("1 pc", "1/4 kg", "1/2 kg", "1 kg").forEach { option ->
                    Button(
                        onClick = { selectedWeight = option },
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedWeight == option) Color(0xFF1D7151) else Color.White,
                            contentColor = if (selectedWeight == option) Color.White else Color.Black
                        ),
                        elevation = ButtonDefaults.buttonElevation(8.dp)
                    ) {
                        Text(text = option)
                    }
                }
            }

        }

        Spacer(modifier = Modifier.weight(1f))

        // Footer section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1D7151))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Total Price",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "₱ ${"%.2f".format(totalPrice)}",
                    fontSize = 15.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                onClick = { /* Add to Basket */ },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Add to Basket",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
