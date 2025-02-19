package com.example.splashscreenbaskit.Tagabili

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.compose.rememberNavController
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily

@Preview(showBackground = true)
@Composable
fun TB_OrdersActivityPreview() {
    TB_OrdersContent(navController = rememberNavController())
}

@Composable
fun TB_OrdersContent(navController: NavController) {
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(257.dp)
                    .background (Color(0xFF1D7151))
            ){
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(top = 45.dp, start = 25.dp)
                        .size(35.dp)
                        .background(Color.White, shape = RoundedCornerShape(50))
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }

                Text(
                    text = "CUSTOMER'S\nORDERS",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFontFamily,
                    modifier = Modifier.padding(top = 150.dp, start = 30.dp)
                )


            }
            Spacer(modifier = Modifier.height(45.dp))

            TB_CustomerInfo ()

            Spacer(modifier = Modifier.height(45.dp))

            TB_OrderItems()
        }
    }
}

@Composable
fun TB_CustomerInfo (){
    Row {
        Text(
            text = "Name:",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFontFamily,
            modifier = Modifier.padding(start = 30.dp)
        )

        Text(
            text = "Jorose",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = poppinsFontFamily,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
    Spacer(modifier = Modifier.height(10.dp))

    Row {
        Text(
            text = "Branch:",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFontFamily,
            modifier = Modifier.padding(start = 30.dp)
        )

        Text(
            text = "Dagupan",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = poppinsFontFamily,
            modifier = Modifier.padding(start = 10.dp)
        )
    }

    Spacer(modifier = Modifier.height(10.dp))

    Row {
        Text(
            text = "Contact No.:",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFontFamily,
            modifier = Modifier.padding(start = 30.dp)
        )

        Text(
            text = "0900-000-0000",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = poppinsFontFamily,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun TB_OrderItems(){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "ITEMS",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFontFamily
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(top = 12.dp, start = 30.dp, end = 30.dp)
                .background (Color(0xFFF2F2F2), shape = RoundedCornerShape(20.dp))
        ){
            Row (modifier = Modifier.padding(20.dp)
            ){
                Column {
                    Text(text = "Product",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFontFamily
                    )

                    Row {
                        Text(text = "1 pc",
                            color = Color.Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = poppinsFontFamily
                        )

                        Text(text = "Quantity:",
                            color = Color.Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = poppinsFontFamily,
                            modifier = Modifier.padding(start = 15.dp)
                        )

                        Text(text = "1",
                            color = Color.Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = poppinsFontFamily,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }

                Text(
                    text = "₱ 00.00",
                    color = Color.DarkGray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFontFamily,
                    modifier = Modifier.padding(start = 120.dp, top = 10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))
    }

    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Text(text = "Total",
            color = Color(0xFF83BD70),
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = poppinsFontFamily
        )

        Spacer(modifier = Modifier.width(120.dp))

        Text(text = "₱ 00.00",
            color = Color(0xFF83BD70),
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = poppinsFontFamily
        )
    }

    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Button(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .height(50.dp)
                .width(148.dp)
            ,
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1d7151)),
            enabled = true
        ) {
            Text("Decline",
                color = Color(0xFF83BD70),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = poppinsFontFamily
            )
        }

        Spacer(modifier = Modifier.width(25.dp))

        Button(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .height(50.dp)
                .width(148.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1d7151)),
            enabled = true
        ) {
            Text("Accept",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = poppinsFontFamily
            )
        }
    }

}
