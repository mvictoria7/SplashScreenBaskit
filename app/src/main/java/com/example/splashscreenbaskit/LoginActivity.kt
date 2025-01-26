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
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.splashscreenbaskit.R

@Preview(showBackground = true)
@Composable
fun LoginActivityPreview() {
    LogInScreen(navController =  rememberNavController())
}
@Composable
fun LogInScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val options = listOf("Guest", "Tagabili")
    var selectedOption by remember { mutableStateOf(options[0]) }

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
                .size(200.dp)
                .padding(bottom = 16.dp)
        )


        Text(
            text = "Log into your account",
            color = Color.Gray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 5.dp)
                .align(Alignment.CenterHorizontally),
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            label = { Text(text = "Username or Email") },
            modifier = Modifier.fillMaxWidth(0.8f ),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(10.dp)
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

        Row (modifier = Modifier.padding(5.dp)) {
            options.forEach { text ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { selectedOption = text }
                    )
                    Spacer(modifier = Modifier.width(0.dp))
                    Text(text = text)
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                Log.i("Credential", "Email: $email, Password: $password")
                navController.navigate("Home")
            },
            modifier = Modifier.fillMaxWidth(0.8f)
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1d7151),
                contentColor = Color.White
            )

        ) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(150.dp))
        //Spacer(modifier = Modifier.weight(1f))

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
                onClick = { navController.navigate ("SignUpActivity") },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = "Sign Up", fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(textDecoration = TextDecoration.Underline,
                    color = Color(0xFF1d7151))
                )

            }
        }
    }

}

