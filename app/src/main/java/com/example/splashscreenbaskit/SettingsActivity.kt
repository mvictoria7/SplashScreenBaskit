package com.example.splashscreenbaskit

import android.widget.Switch
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun SettingsActivity () {

    var isChecked by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xAA1d7151).copy(alpha = 1f))
    ) {
        //notif and settings button


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp, start = 15.dp),
            horizontalArrangement = Arrangement.Start
        ) {

            IconButton(
                onClick = { },
                enabled = true,
                modifier = Modifier
                    .padding(top = 5.dp, end = 0.dp)
                    .size(50.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Notifications",
                    tint = Color.White
                )
            }
        }


        // Inner Box
        Box(
            modifier = Modifier
                .height(680.dp)
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
                    text = "Settings",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Black
                )

                Spacer(modifier = Modifier.height(40.dp))

                //username
                Column {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFF1F1F1), RoundedCornerShape(10.dp))
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.padding(15.dp)
                            ) {
                                Text(
                                    text = "Receive push notifications",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold)

                                //Switch(
                                    //checked = isChecked,
                                    //onCheckedChange = { isChecked = it }
                                //)
                            }
                        }

                    }
                }
            }
        }
    }
}