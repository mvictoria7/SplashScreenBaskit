package com.example.splashscreenbaskit.AccountDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.window.DialogProperties

@Preview(showBackground = true)
@Composable
fun AccountActivity() {
    AccountActivity(navController = rememberNavController())
}

@Composable
fun AccountActivity(navController: NavController) {
    // State to control dialog visibility
    val showDialog = remember { mutableStateOf(false) }

    // Show dialog when the user clicks "Log Out"
    val onLogOutClick = {
        showDialog.value = true
    }

    // Handle "Yes" and "No" actions from the dialog
    val onConfirmLogOut = {
        // Navigate to the login screen
        navController.navigate("LoginActivity")
        showDialog.value = false
    }

    val onCancelLogOut = {
        // Just close the dialog without logging out
        showDialog.value = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xAA1d7151).copy(alpha = 1f))
    ) {
        //notif and settings button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp, end = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {

            IconButton(
                onClick = { navController.navigate("NotificationsActivity") },
                enabled = true,
                modifier = Modifier
                    .padding(top = 5.dp, end = 0.dp)
                    .size(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notifications",
                    tint = Color.White
                )
            }

            IconButton(
                onClick = { navController.navigate("SettingsActivity") },
                enabled = true,
                modifier = Modifier.padding(5.dp).size(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings",
                    tint = Color.White
                )

            }
        }


        // Inner Box
        Box(
            modifier = Modifier
                .height(680.dp)
                .width(420.dp)
                .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .align(Alignment.BottomCenter)
                .background(Color.White)
        ) {

            //inner Box

            //Account details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp, top = 50.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Account Details",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Black,

                    )

                Spacer(modifier = Modifier.height(40.dp))

                //username
                Column {

                    Text(
                        text = "Username",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "")

                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF1F1F1), RoundedCornerShape(10.dp))
                        ) {
                            Row (
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.padding(15.dp)
                            ){
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    tint = Color.DarkGray
                                )
                                Text(text = "mariavicky")
                            }
                        }

                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                //birthday
                Column {

                    Text(
                        text = "Birthdate",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "")

                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF1F1F1), RoundedCornerShape(10.dp))

                        ) {
                            Row (verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.padding(15.dp)
                            ){
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = null,
                                    tint = Color.DarkGray
                                )
                                Text(text = "02/15/2005")
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                //email
                Column {

                    Text(
                        text = "Email",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "")

                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF1F1F1), RoundedCornerShape(10.dp))
                        ) {
                            Row (verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.padding(15.dp)
                            ){
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = null,
                                    tint = Color.DarkGray
                                )
                                Text(text = "test@gmail.com")
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                //password
                Column {

                    Text(
                        text = "Password",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "")

                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF1F1F1), RoundedCornerShape(10.dp))
                        ) {
                            Row (verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.padding(15.dp)
                            ){
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = null,
                                    tint = Color.DarkGray
                                )
                                Text(text = "********")
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                //reset password
                Column (horizontalAlignment = Alignment.End
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Reset password",
                            fontSize = 12.sp,
                            color = Color(0xFF4557FF),
                            modifier = Modifier.clickable {  }
                        )
                    }
                }

                Column ( modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.height(50.dp))

                    // Log Out button
                    Button(
                        onClick = onLogOutClick,
                        modifier = Modifier
                            .width(170.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(100.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCA0000), contentColor = Color.White)
                    ) {
                        Text(text = "Log Out")
                    }
                }
            }
        }

        // Confirmation Dialog
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text("Log Out") },
                text = { Text("Are you sure you want to log out?") },
                confirmButton = {
                    Button(
                        onClick = {
                            navController.navigate(route = "LoginActivity")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCA0000))
                    ) {
                        Text("Log out", color = Color.White)
                    }
                },
                dismissButton = {
                    Button(
                        onClick = onCancelLogOut,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                    ) {
                        Text("Cancel", color = Color.White)
                    }
                },
                containerColor = Color.White,
                properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
            )
        }
    }
}