package com.example.splashscreenbaskit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//ayusin pa dis


@Preview(showBackground = true)
@Composable
fun SettingsActivity () {
    var notificationsEnabled by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){

        //circle header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1D7151), RoundedCornerShape(bottomStart = 180.dp, bottomEnd = 180.dp))
                //.padding(10.dp)
                .height(350.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = R.drawable.baskitlogo_white),
                    contentDescription = "Baskit Icon",
                    tint = Color.White,
                    modifier = Modifier.height(170.dp)
                )
                Text(
                    text = "Shop Smarter, Not Harder",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }

    Column(modifier = Modifier.padding(top = 400.dp, start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            fontWeight = FontWeight.Black
        )

        Spacer(modifier = Modifier.height(40.dp))

        Column {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF1F1F1), RoundedCornerShape(20.dp))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(15.dp)
                    ) {
                        Text(
                            text = "Receive push notifications",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Switch(
                            checked = notificationsEnabled,
                            onCheckedChange = { notificationsEnabled = it },
                            modifier = Modifier
                                .padding(start = 50.dp)
                                .size(30.dp),
                            colors = SwitchDefaults.colors(
                                //checkedThumbColor = Color.Green,
                                //uncheckedThumbColor = Color.Red,
                                checkedTrackColor = Color.DarkGray,
                                uncheckedTrackColor = Color.LightGray
                            )
                        )

                    }
                }

            }
        }
    }
}

