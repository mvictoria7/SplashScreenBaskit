package com.example.splashscreenbaskit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AppleScreen(navController: NavController) {  // ✅ Accept NavController
    var quantity by remember { mutableStateOf(1) }
    var selectedWeight by remember { mutableStateOf("1 pc") }

    // Base price for 1 pc
    val basePrice = 32.25
    val priceIncrease = 30.0

    // Price calculation based on selected weight
    val priceForWeight = when (selectedWeight) {
        "1 pc" -> basePrice
        "1/4 kg" -> basePrice + priceIncrease
        "1/2 pc" -> basePrice + priceIncrease
        "1 kg" -> basePrice + priceIncrease * 2
        else -> basePrice
    }

    val totalPrice = (priceForWeight * quantity).toFloat()  // ✅ Ensure totalPrice is Float
    val weightOptions = listOf("1 pc", "1/4 kg", "1/2 pc", "1 kg")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Image section with back button
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
                onClick = { navController.popBackStack() },  // ✅ Navigate back
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

        // Product details
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        ) {
            Text(
                text = "Apple",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            // Star rating section
            Row(verticalAlignment = Alignment.CenterVertically) {
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

            Text(
                text = "₱ ${"%.2f".format(priceForWeight)} per unit",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        // Weight options
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            weightOptions.forEach { option ->
                Button(
                    modifier = Modifier
                        .height(52.dp)
                        .padding(1.dp)
                        .width(80.dp),
                    onClick = { selectedWeight = option },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedWeight == option) Color.Green else Color.White,
                        contentColor = Color.Black
                    ),
                    elevation = ButtonDefaults.buttonElevation(2.dp)
                ) {
                    Text(
                        text = option,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Vendor section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.lettuce),
                contentDescription = "Vendor Profile",
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .size(35.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Martha Rosario (Aling Martha’s)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "0900-000-0000",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Footer section
        Row(
            modifier = Modifier
                .height(114.dp)
                .fillMaxWidth()
                .background(Color(0xFF1D7151))
                .padding(30.dp),
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

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "₱ ${"%.2f".format(totalPrice)}",
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth(),
                onClick = {
                    navController.navigate("Addtocart/${selectedWeight}/${quantity}/${totalPrice}")
                },
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
            ) {
                Text(
                    text = "Add to Basket",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}
