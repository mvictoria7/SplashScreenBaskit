package com.example.splashscreenbaskit

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview (showBackground = true)
@Composable
fun SignUpActivity() {

    var username by remember {mutableStateOf("")}
    var email by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) { }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(id = R.drawable.signup_image),
            contentDescription = "Sign Up image",
            modifier = Modifier.size(250.dp)
        )

        //Text(text = "Welcome Back", fontSize = 30.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Create your account",
            color = Color.Gray,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                //.fillMaxWidth(0.8f)
                .padding(bottom = 5.dp))

        //Spacer(modifier = Modifier.height(15.dp))

        //name text field
        OutlinedTextField(value = username,
            onValueChange = {email = it},
            label = {Text(text = "Username")},
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        //email text field
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = {Text(text = "Email address")},
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null)},
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        //password text field
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = {Text(text = "Password")},
            leadingIcon = { Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null)},
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(10.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = {Text(text = "Confirm Password")},
            leadingIcon = { Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null)},
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(10.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = { Log.i ("Credential", "Email: $email Password : $password") },
            modifier = Modifier
                .width (180.dp)
                .height(50.dp),
            shape = RoundedCornerShape(100.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1d7151)),
            enabled = true
        ) {
            Text(text = "Sign up", modifier = Modifier.clickable {  })
        }

        Spacer(modifier = Modifier.height(100.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Already have an account?",
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            TextButton (
                onClick = { },
                enabled = true
            ){
                Text(text = "Sign up",
                    color = Color(0xFF1d7151),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(textDecoration = TextDecoration.Underline))
            }
        }



    }

}

