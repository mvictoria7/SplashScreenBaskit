package com.example.splashscreenbaskit.LoginSignup

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily

//import com.example.myapplication.design.loginregister.LogInScreen

@Preview(showBackground = true)
@Composable
fun SignUpActivityPreview() {
    SignUpActivity(navController =  rememberNavController())
}
@Composable
fun SignUpActivity(navController: NavController) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(25.dp))

            Image(
                painter = painterResource(id = R.drawable.baskit_logo),
                contentDescription = "Sign Up image",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Create your account",
                color = Color.Gray,
                fontFamily = poppinsFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 5.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Row for First Name and Last Name
            Row(
                modifier = Modifier.fillMaxWidth(0.8f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // First Name field
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text(text = "First Name", fontFamily = poppinsFontFamily)},
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),

                )

                // Last Name field
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text(text = "Last Name", fontFamily = poppinsFontFamily) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp)
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            OutlinedTextField(
                value = birthday,
                onValueChange = { newValue ->
                    val digitsOnly = newValue.filter { it.isDigit() }
                    val formattedDate = buildString {
                        for (i in digitsOnly.indices) {
                            if (i == 2 || i == 4) append("/")
                            append(digitsOnly[i])
                        }
                    }
                    if (formattedDate.length <= 10) {
                        birthday = formattedDate
                    }
                },
                label = { Text("Birthday", fontFamily = poppinsFontFamily) },
                placeholder = { Text("MM/DD/YYYY") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Calendar Icon"
                    )
                },
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(2.dp))

            // username field
            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text(text = "Username", fontFamily = poppinsFontFamily,) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "Person Icon")
                },
                modifier = Modifier.fillMaxWidth(0.8f) ,
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(2.dp))

            // number field
            OutlinedTextField(
                value = contactNumber,
                onValueChange = { contactNumber = it },
                label = { Text(text = "Contact Number", fontFamily = poppinsFontFamily) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone Icon")
                },
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Email field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email", fontFamily = poppinsFontFamily,) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "Email Icon")
                },
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Password field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password", fontFamily = poppinsFontFamily) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Password Icon")
                },
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(10.dp),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Confirm Password field
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(text = "Confirm Password", fontFamily = poppinsFontFamily,) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Confirm Password Icon"
                    )
                },
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(10.dp) ,
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(1.dp))

            // Terms and Conditions
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 0.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it }
                )
                Spacer(modifier = Modifier.width(2.dp))

                Text(text = "Agree to Terms and Conditions", fontFamily = poppinsFontFamily, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.width(10.dp))

            // Sign Up button
            Button(
                onClick = {
                    if (password != confirmPassword) {
                        Log.e("SignUp", "Passwords do not match")
                    } else {
                        Log.i(
                            "Credential",
                            "First Name: $firstName, Last Name: $lastName, Email: $email, Password: $password"
                        )
                    }
                },
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(100.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1d7151)),
                enabled = firstName.isNotBlank() && lastName.isNotBlank() &&
                        email.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()
            ) {
                Text(text = "Sign up", fontFamily = poppinsFontFamily)
            }

//            Button(
//                onClick = {
//                    if (email.isNotBlank() && password.isNotBlank()) {
//                        val request = LoginRequest(email, password)
//
////                        CoroutineScope(Dispatchers.Main).launch {
////                            try {
////                                val response = RetrofitInstance.instance.login(request)
////                                if (response.isSuccessful) {
////                                    val token = response.body()?.token
////                                    Log.d("LoginSuccess", "Token: $token")
////                                    navController.navigate("HomeActivity")
////                                } else {
////                                    Log.e("LoginFailed", "Error: ${response.message()}")
////                                }
////                            } catch (e: Exception) {
////                                Log.e("LoginError", "Error: ${e.message}")
////                            }
////                        }
//                    }
//                },
//                modifier = Modifier.fillMaxWidth(0.8f).height(50.dp),
//                enabled = email.isNotBlank() && password.isNotBlank(),
//                shape = RoundedCornerShape(10.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF1d7151),
//                    contentColor = Color.White
//                )
//            ) {
//                Text(text = "Sign Up")
//            }

            Spacer(modifier = Modifier.height(30.dp))

            //Already have an account
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an account?",
                    fontFamily = poppinsFontFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                TextButton(
                    onClick = { navController.navigate("LoginActivity")},
                    enabled = true
                ) {
                    Text(
                        text = "Log In",
                        color = Color(0xFF1d7151),
                        fontFamily = poppinsFontFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        style = TextStyle(textDecoration = TextDecoration.Underline)
                    )


                }
            }
        }
    }
}







