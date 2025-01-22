package com.example.splashscreenbaskit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
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

            //Acount details
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
                            value = email,
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

                Spacer(modifier = Modifier.height(30.dp))

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
            }


            Spacer(modifier = Modifier.height(30.dp))

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


        }
    }
}













