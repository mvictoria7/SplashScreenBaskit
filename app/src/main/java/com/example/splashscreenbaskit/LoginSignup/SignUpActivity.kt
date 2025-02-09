package com.example.splashscreenbaskit.LoginSignup

import android.app.AlertDialog
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily

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
    var showTerms by remember { mutableStateOf(false) }

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
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
            )

            //Spacer(modifier = Modifier.height(15.dp))

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



            Spacer(modifier = Modifier.height(8.dp))

            TermsAndConditions(isChecked) { newValue -> isChecked = newValue }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (password != confirmPassword) {
                        Log.e("SignUp", "Passwords do not match")
                    } else {
                        Log.i("SignUp", "User Registered: $email")
                    }
                },
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsAndConditions(isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = onCheckedChange
            )

            Text(
                text = "Agree to Terms and Conditions",
                fontSize = 12.sp,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = { showDialog = true }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand Terms"
                )
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                modifier = Modifier.fillMaxSize()
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(0.dp),
                    color = Color.White
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Terms and Conditions",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = """
                                Welcome to Baskit! These Terms and Conditions govern your use of our delivery app and services.
                                By accessing or using Baskit, you agree to comply with these terms.

                                You must be at least 18 years old to use Baskit and agree to provide accurate personal information when creating an account.
                                You are responsible for maintaining the confidentiality of your login details.
                                Orders placed through the app are subject to availability and acceptance by merchants.
                                Prices displayed on the app include applicable charges unless stated otherwise, and payment must be completed before order confirmation.

                                Delivery times are estimated and may vary due to unforeseen circumstances.
                                Users must provide accurate delivery addresses, and if a recipient is unavailable, the order may be canceled or rescheduled at the user’s cost.
                                Orders can only be canceled before they are accepted by the merchant.
                                Refunds, if applicable, will be processed according to Baskit’s refund policy.

                                Users must not misuse the app, engage in fraud, or harass others.
                                Baskit reserves the right to suspend or terminate accounts that violate these terms.
                                We act as an intermediary between users and merchants and are not responsible for product quality.
                                Additionally, we are not liable for delays or losses due to factors beyond our control.

                                By using Baskit, you agree to our Privacy Policy regarding data collection and usage.
                                We reserve the right to update these terms at any time, and continued use of the app signifies acceptance of any modifications.
                                If you have any questions or concerns, please contact us at Baskit.
                            """.trimIndent(),
                            fontSize = 12.sp,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = { onCheckedChange(false); showDialog = false },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                            ) {
                                Text("Decline")
                            }

                            Button(
                                onClick = { onCheckedChange(true); showDialog = false },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1d7151))
                            ) {
                                Text("Accept")
                            }
                        }
                    }
                }
            }
        }
    }
}
