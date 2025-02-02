package com.example.splashscreenbaskit

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//ayusin pa dis

@Preview(showBackground = true)
@Composable
fun NotificationsActivity () {

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
                .padding(10.dp)
                .height(340.dp)
                .width(400.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = R.drawable.baskitlogo_white),
                    contentDescription = "Basket Icon",
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
            text = "Notifications",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            fontWeight = FontWeight.Black
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {  },
            modifier = Modifier
                .padding(start = 250.dp)
                .width(70.dp)
                .height(30.dp)
        ){
            Text(
                text = "Sign Up",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.padding(0.dp)
            )
        }

        Button(
            onClick = {},
            modifier = Modifier
                .width(70.dp)
                .height(30.dp),
            shape = RoundedCornerShape(100.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD9D9D9)),
        ) {
            Text(text = "Clear all",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.padding(0.dp))

        }

    }
}