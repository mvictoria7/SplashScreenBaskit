package com.example.myapplication.design.loginregister

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import com.example.splashscreenbaskit.R


@Composable
fun LogInScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(id = R.drawable.baskit_logo),
            contentDescription = "Login Image",
            modifier = Modifier
                .size(300.dp)
                .padding(bottom = 16.dp)
        )


        Text(
            text = "Log into your account",
            fontSize = 16.sp,
            modifier = Modifier
                .padding(bottom = 5.dp)
                .align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            label = { Text(text = "Username or Email Address",  style = TextStyle(fontSize = 14.sp)) },
            modifier = Modifier.fillMaxWidth(0.8f)

        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(4.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Forgot password?",
                fontSize = 12.sp,
                color = Color(0xFF006400),
                modifier = Modifier.clickable {  }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                Log.i("Credential", "Email: $email, Password: $password")
            },
            modifier = Modifier.fillMaxWidth(0.8f)
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3A6E51),
                contentColor = Color.White
            )

        ) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have an account?",
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            TextButton(
                onClick = {  },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = "Sign Up", fontSize = 14.sp,
                    style = TextStyle(textDecoration = TextDecoration.Underline,
                        color = Color(0xFF006400)))

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LogInScreenPreview() {
    LogInScreen()
}

