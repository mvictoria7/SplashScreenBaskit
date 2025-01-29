package com.example.splashscreenbaskit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun CartActivity()
{
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
                painter = painterResource(id = R.drawable.lettuce),
                contentDescription = "Lettuce",
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

        // Product details
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        ) {
            Column {
                Text(
                    text = "Lettuce",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    //modifier = Modifier.padding(vertical = 4.dp)
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

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 100.dp)
                    ) {
                        IconButton(onClick = {  }) {
                            Icon(
                                painter = painterResource(id = R.drawable.minus),
                                contentDescription = "Minus",
                                tint = Color.Black,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Text(
                            text = "2",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        IconButton(onClick = { /* Increase quantity */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.add),
                                contentDescription = "Add",
                                tint = Color.Black,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }

                Text(
                    text = "₱ 32.25",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }


            Spacer(modifier = Modifier.height(40.dp))


            // Counter Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Product Description",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }


            Spacer(modifier = Modifier.height(10.dp))


            Text(
                text = "Freshly grown lettuce from Aling Nena's own garden \nFresh every day!",
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(80.dp))

            // Weight options
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOf("1 pc", "1/4 kg", "1/2 pc", "1 kg").forEach { option ->
                    Button(
                        modifier = Modifier
                            .height(52.dp)
                            .padding(0.dp)
                            .width(70.dp),
                        onClick = { },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        elevation = ButtonDefaults.buttonElevation(2.dp)
                    ) {
                        Text(
                            //modifier = Modifier.padding(0.dp),
                            text = option,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold)
                    }
                }
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
                    text = "₱ 64.50",
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                modifier = Modifier.height(55.dp),
                onClick = {  },
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Add to Basket",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}