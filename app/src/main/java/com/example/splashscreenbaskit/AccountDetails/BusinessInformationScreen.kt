package com.example.splashscreenbaskit.AccountDetails

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily

@Composable
fun BusinessInformationScreen(navController: NavController) {
    var shopName by remember { mutableStateOf("") }
    var shopAddress by remember { mutableStateOf("") }
    var selectedStoreType by remember { mutableStateOf("Partnership") } // Default selection
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

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
                    .size(40.dp)
                    .background(Color(0xFF2E7D32), CircleShape), // Checked step
                contentAlignment = Alignment.Center
            ) {
                Text(text = "✔", color = Color.White, fontSize = 14.sp)
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
                    .background(Color(0xFF2E7D32), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "2", color = Color.White, fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Business Information", fontWeight = FontWeight.Bold, fontSize = 24.sp, fontFamily = poppinsFontFamily)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Shop Name
            Text(text = "Registered Shop Name", fontSize = 16.sp, color = Color.Gray)
            OutlinedTextField(
                value = shopName,
                onValueChange = { shopName = it },
                label = { Text("Enter your shop name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Shop Address
            Text(text = "Registered Shop Address", fontSize = 16.sp, color = Color.Gray)
            OutlinedTextField(
                value = shopAddress,
                onValueChange = { shopAddress = it },
                label = { Text("Enter your shop address") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Certificate of Registration Upload
            Text(text = "Certificate of Registration", fontSize = 16.sp, color = Color.Gray)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.LightGray, RoundedCornerShape(10.dp))
                    .clickable {
                        Toast
                            .makeText(context, "Upload Certificate", Toast.LENGTH_SHORT)
                            .show()
                    },
                contentAlignment = Alignment.CenterStart
            ) {
                Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("Upload Image", color = Color.DarkGray, fontSize = 16.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.save),
                        contentDescription = "Upload Icon",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            // Valid ID Upload
            Text(text = "Valid ID", fontSize = 16.sp, color = Color.Gray)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.LightGray, RoundedCornerShape(10.dp))
                    .clickable {
                        Toast
                            .makeText(context, "Upload Valid ID", Toast.LENGTH_SHORT)
                            .show()
                    },
                contentAlignment = Alignment.CenterStart
            ) {
                Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("Upload Image", color = Color.DarkGray, fontSize = 16.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.save),
                        contentDescription = "Upload Icon",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            // Store Type Selection
            Text(text = "Choose your store type", fontSize = 16.sp, color = Color.Gray)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(
                    onClick = { selectedStoreType = "Standard" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedStoreType == "Standard") Color(0xFF5CC163) else Color.LightGray
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                ) {
                    Text("Standard", color = Color.White)
                }

                Button(
                    onClick = { selectedStoreType = "Partnership" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedStoreType == "Partnership") Color(0xFF5CC163) else Color.LightGray
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Partnership", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // "Send Request" Button
            Button(
                onClick = {navController.navigate("RequestSentScreen")},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(10.dp),
            ) {
                Text("Send Request", color = Color.White)
            }
        }
    }
}
