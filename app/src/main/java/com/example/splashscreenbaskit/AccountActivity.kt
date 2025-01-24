package com.example.splashscreenbaskit

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.design.loginregister.LogInScreen

@Preview (showBackground = true)
@Composable
fun AccountActivity () {

    val email by remember { mutableStateOf("") }

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
                onClick = { },
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
                onClick = { },
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
                .height(650.dp)
                .width(420.dp)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .align(Alignment.BottomCenter)
                .background(Color.White)
        ) {

            //inner Box

            //Account details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Account \ndetails",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Black
                )

                Spacer(modifier = Modifier.height(40.dp))

                //username
                Column {

                    Text(
                        text = "Username",
                        fontSize = 12.sp,
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

                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                //birthday
                Column {

                    Text(
                        text = "Birthdate",
                        fontSize = 12.sp,
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

                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                //email
                Column {

                    Text(
                        text = "Email",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = email)

                        OutlinedTextField(
                            value = email,
                            onValueChange = {},
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                Box(
                    modifier = Modifier
                        //.padding(16.dp)
                        .border(0.5.dp, Color.Black, RoundedCornerShape(10.dp))
                        .padding(15.dp) //inside padding
                        //.width


                ) {
                    Text(text = "This is some text inside a box")
                }

                //email
                Column {

                    Text(
                        text = "Password",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = email)

                        OutlinedTextField(
                            value = email,
                            onValueChange = {},
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Column (
                    //modifier = Modifier.fillMaxWidth(),
                    //.width(180.dp),
                    //verticalArrangement = Arrangement,
                    horizontalAlignment = Alignment.End
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "Reset password",
                            fontSize = 12.sp,
                            color = Color(0xFF006400),
                            modifier = Modifier.clickable {  }
                        )
                    }
                }

                Column ( modifier = Modifier.fillMaxWidth(),
                    //.width(180.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.height(20.dp))

                    // Log Out button
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp),
                        shape = RoundedCornerShape(100.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1d7151),
                        contentColor = Color.White
                        )

                    ) {
                        Text(text = "Log Out")
                    }
                }


            }

        }
    }
}














