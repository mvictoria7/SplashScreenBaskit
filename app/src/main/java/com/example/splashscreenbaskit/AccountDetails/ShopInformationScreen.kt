package com.example.splashscreenbaskit.AccountDetails

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
            .padding(16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Back Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "< Back",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFontFamily,
                modifier = Modifier.clickable { navController.popBackStack() }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Page Indicator
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color(0xFF2E7D32), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "1", color = Color.White, fontSize = 14.sp)
            }
            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .width(60.dp)
                    .padding(horizontal = 8.dp)
                    .height(4.dp)
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Gray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "2", color = Color.White, fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Shop Information", fontWeight = FontWeight.Bold, fontSize = 30.sp, fontFamily = poppinsFontFamily)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Shop Name
            Text(text = "Shop Name", fontSize = 16.sp, color = Color.Gray)
            OutlinedTextField(
                value = shopName,
                onValueChange = { shopName = it },
                label = { Text("Enter your shop name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Shop Address
            Text(text = "Shop Address", fontSize = 16.sp, color = Color.Gray)
            OutlinedTextField(
                value = shopAddress,
                onValueChange = { shopAddress = it },
                label = { Text("Enter your address") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Owner Name
            Text(text = "Owner Name", fontSize = 16.sp, color = Color.Gray)
            OutlinedTextField(
                value = ownerName,
                onValueChange = { ownerName = it },
                label = { Text("Enter a name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Owner Number
            Text(text = "Owner Number", fontSize = 16.sp, color = Color.Gray)
            OutlinedTextField(
                value = ownerNumber,
                onValueChange = { ownerNumber = it },
                label = { Text("Enter your number") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // "Next" Button - Disabled if any field is empty
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
