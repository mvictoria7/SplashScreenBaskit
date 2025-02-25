package com.example.splashscreenbaskit.AccountDetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily

@Preview
@Composable
fun ShopInfoPreview (){
    ShopInformationScreen(navController = rememberNavController())
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopInformationScreen(navController: NavController) {
    var shopName by remember { mutableStateOf("") }
    var shopAddress by remember { mutableStateOf("") }
    var ownerName by remember { mutableStateOf("") }
    var ownerNumber by remember { mutableStateOf("") }

    val isButtonEnabled = shopName.isNotBlank() && shopAddress.isNotBlank() && ownerName.isNotBlank() && ownerNumber.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "< Back",
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontFamily = poppinsFontFamily,
            modifier = Modifier
                .padding(start = 20.dp)
                .clickable { navController.popBackStack() }
        )

        Spacer(modifier = Modifier.height(40.dp))

        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFF1D7151), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "1",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFontFamily
                    )
                }
                Divider(
                    color = Color(0xFFD9D9D9),
                    modifier = Modifier
                        .width(60.dp)
                        .height(2.dp)
                )
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFA2A2A2), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "2",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFontFamily)
                }
            }
            Spacer(modifier = Modifier.height(65.dp))

            Text(
                "Shop Information",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                fontFamily = poppinsFontFamily
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 40.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    modifier = Modifier.padding(bottom = 5.dp),
                    text = "Shop Name",
                    fontSize = 14.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black)
                OutlinedTextField(
                    value = shopName,
                    onValueChange = { shopName = it },
                    label = {
                        Text("Enter your shop name",
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily,
                            color = Color(0xFF8C8C8C)
                        )
                    },
                    modifier = Modifier.fillMaxWidth() .height(45.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(modifier = Modifier.padding(bottom = 5.dp),
                    text = "Shop Address",
                    fontSize = 14.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black)
                OutlinedTextField(
                    value = shopAddress,
                    onValueChange = { shopAddress = it },
                    label = {
                        Text("Enter your shop address",
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily,
                            color = Color(0xFF8C8C8C)
                        )
                    },                    modifier = Modifier.fillMaxWidth() .height(45.dp),
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(modifier = Modifier.padding(bottom = 5.dp),
                    text = "Owner Name",
                    fontSize = 14.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black)
                OutlinedTextField(
                    value = ownerName,
                    onValueChange = { ownerName = it },
                    label = {
                        Text("Enter your name",
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily,
                            color = Color(0xFF8C8C8C)
                        )
                    },                    modifier = Modifier.fillMaxWidth().height(45.dp),
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(modifier = Modifier.padding(bottom = 5.dp),
                    text = "Owner Number",
                    fontSize = 14.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black)
                OutlinedTextField(
                    value = ownerNumber,
                    onValueChange = { ownerNumber = it },
                    label = {
                        Text("Enter your number",
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily,
                            color = Color(0xFF8C8C8C)
                        )
                    },                    modifier = Modifier.fillMaxWidth() .height(45.dp),
                    shape = RoundedCornerShape(10.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(50.dp))

                Button(
                    onClick = {navController.navigate("BusinessInformationScreen")},
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    enabled = isButtonEnabled,
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text("Next", color = Color.White)
                }
            }
        }
    }
}
