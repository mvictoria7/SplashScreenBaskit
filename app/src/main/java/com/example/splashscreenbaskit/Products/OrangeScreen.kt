package com.example.splashscreenbaskit.Products

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily

@Preview(showBackground = true)
@Composable
fun OrangeScreenPreview() {
    OrangeScreen(navController = rememberNavController())
}

@Composable
fun OrangeScreen(navController: NavHostController) {
    var quantity by remember { mutableIntStateOf(1) }
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
                .height(330.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.orange),
                contentDescription = "Apple",
                modifier = Modifier
                    .height(330.dp)
                    .width(400.dp),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { navController.navigate("HomeActivity") },
                modifier = Modifier
                    .padding(top = 45.dp, start = 25.dp)
                    .align(Alignment.TopStart)
                    .size(35.dp)
                    .background(Color.White, shape = RoundedCornerShape(50))
            ) {
                Icon(
                    modifier = Modifier.size(15.dp),
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
                .padding(top = 10.dp, start = 25.dp, end = 25.dp)
        ) {
            Text(
                text = "Orange",
                fontSize = 32.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                /*repeat(4) {
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
                )*/

                Spacer(modifier = Modifier.padding(start = 225.dp) )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { if (quantity > 1) quantity-- },
                        modifier = Modifier
                            .background(color = Color(0xFFD9D9D9), shape = CircleShape)
                            .size(35.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.minus),
                            contentDescription = "Minus",
                            tint = Color.Black,
                            modifier = Modifier.size(15.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "$quantity",
                        fontSize = 20.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    IconButton(
                        onClick = { quantity++ },
                        modifier = Modifier
                            .background(color = Color(0xFFD9D9D9), shape = CircleShape)
                            .size(35.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = "Add",
                            tint = Color.Black,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }
            }

            Text(
                text = "₱ ${"%.2f".format(priceForWeight)}",
                fontSize = 24.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold,
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
                    text = "Seller Description",
                    fontSize = 24.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Martha Rosario (Aling Martha’s)",
                fontSize = 16.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Text(
                text = "0900-000-0000",
                fontSize = 16.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Light,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Weight options
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("1 pc", "1/4 kg", "1/2 kg", "1 kg").forEach { option ->
                    Button(
                        modifier = Modifier
                            .height(48.dp)
                            .width(75.dp)
                            //.clip(RoundedCornerShape(10.dp))
                            .then(
                                if (selectedWeight != option) Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .border(BorderStroke(1.dp, Color.LightGray))
                                else Modifier
                            ),
                        onClick = { selectedWeight = option },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedWeight == option) Color(0xFFD9D9D9) else Color.White,
                            contentColor = Color.Black
                        ),
                    ) {
                        Text(text = option,
                            fontSize = 12.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold)
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
                .height(110.dp)
                .padding(25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = "Total Price",
                    fontSize = 16.sp,
                    fontFamily = poppinsFontFamily,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    //modifier = Modifier.padding(start = 0.dp)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "₱ ${"%.2f".format(totalPrice)}",
                    fontSize = 24.sp,
                    fontFamily = poppinsFontFamily,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                onClick = { navController.navigate("LoginActivity") },
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.height(70.dp) .width(180.dp)
            ) {
                Text(
                    text = "Add to Basket",
                    color = Color.Black,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}
