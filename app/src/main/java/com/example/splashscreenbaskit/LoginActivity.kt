package com.example.myapplication.design.loginregister

import LoginRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import LoginResponse
import RetrofitInstance
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.splashscreenbaskit.R

@Preview(showBackground = true)
@Composable
fun LoginActivity() {
    LoginActivity(navController = rememberNavController())
}

@Composable
fun LoginActivity(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val options = listOf("Guest", "Tagabili")
    var selectedOption by remember { mutableStateOf(options[0]) }
    var showDialog by remember { mutableStateOf(false) }
    var newPassword by remember { mutableStateOf("") }
    var showNewPasswordField by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
            modifier = Modifier.fillMaxWidth(0.8f),
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
                color = Color(0xFF4557FF),
                modifier = Modifier.clickable {
                    Log.d("ForgotPassword", "Clicked!")
                    showDialog = true
                }
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(modifier = Modifier.padding(1.dp)) {
            options.forEach { text ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 1.dp)
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { selectedOption = text },
                        /*colors = RadioButtonDefaults.colors(
                            checkedColor = Color.Green,
                            uncheckedColor = Color.LightGray,
                        )*/
                    )
                    Text(
                        text = text,
                        modifier = Modifier.padding(start = 1.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                navController.navigate("HomeActivity")
            },
            modifier = Modifier.fillMaxWidth(0.8f).height(50.dp),
            enabled = email.isNotBlank() && password.isNotBlank(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1d7151),
                contentColor = Color.White
            )
        ) {
            Text(text = "Login")
        }
//            onClick = {
//                if (email.isNotBlank() && password.isNotBlank()) {
//                    val request = LoginRequest(email, password)
//                    CoroutineScope(Dispatchers.Main).launch {
//                        try {
//                            val response = RetrofitInstance.instance.login(request)
//                            if (response.isSuccessful) {
//                                val token = response.body()?.token
//                                Log.d("LoginSuccess", "Token: $token")
//                                navController.navigate("HomeActivity")
//                            } else {
//                                Log.e("LoginFailed", "Error: ${response.message()}")
//                            }
//                        } catch (e: Exception) {
//                            Log.e("LoginError", "Error: ${e.message}")
//                        }
//                    }
//                }
//            },
//            modifier = Modifier.fillMaxWidth(0.8f).height(50.dp),
//            enabled = email.isNotBlank() && password.isNotBlank(),
//            shape = RoundedCornerShape(10.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color(0xFF1d7151),
//                contentColor = Color.White
//            )
//        ) {
//            Text(text = "Login")
//        }

        Spacer(modifier = Modifier.height(150.dp))

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
                onClick = { },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Sign Up", fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    style = TextStyle(textDecoration = TextDecoration.Underline),
                    color = Color(0xFF4557FF)
                )
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Reset Password") },
                text = {
                    Column {
                        Text("Enter a new password")
                        OutlinedTextField(
                            value = newPassword,
                            onValueChange = { newPassword = it },
                            label = { Text("New Password") },
                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        showNewPasswordField = true
                        Log.d("ForgotPassword", "Password reset confirmed")
                    }) {
                        Text("Reset")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
