package com.example.splashscreenbaskit.LoginSignup

import android.app.AlertDialog
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily

// Preview composable for testing the UI
@Preview(showBackground = true)
@Composable
fun SignUpActivityPreview() {
    SignUpActivity(navController = rememberNavController())
}

// Main SignUpActivity composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpActivity(navController: NavController) {
    // State variables for input fields and UI logic
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

    val scrollState = rememberScrollState()

    // Main layout
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(25.dp))

            // App logo
            Image(
                painter = painterResource(id = R.drawable.baskit_logo),
                contentDescription = "Sign Up image",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Title
            Text(
                text = "Create your account",
                color = Color.Gray,
                fontFamily = poppinsFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            // Row for First Name and Last Name
            Row(
                modifier = Modifier.fillMaxWidth(0.8f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // First Name field
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { if (it.all { char -> char.isLetter() }) firstName = it },
                    label = { Text(text = "First Name", fontFamily = poppinsFontFamily) },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray
                    )
                )

                // Last Name field
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { if (it.all { char -> char.isLetter() }) lastName = it },
                    label = { Text(text = "Last Name", fontFamily = poppinsFontFamily) },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray
                    )
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            // Birthday field with formatting
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
                    if (formattedDate.length <= 10) birthday = formattedDate
                },
                label = { Text("Birthday", fontFamily = poppinsFontFamily) },
                placeholder = { Text("MM/DD/YYYY") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = { Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Calendar Icon") },
                modifier = Modifier.fillMaxWidth(0.8f),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Username field
            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text(text = "Username", fontFamily = poppinsFontFamily) },
                leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Person Icon") },
                modifier = Modifier.fillMaxWidth(0.8f),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Contact Number field
            OutlinedTextField(
                value = contactNumber,
                onValueChange = { contactNumber = it },
                label = { Text(text = "Contact Number", fontFamily = poppinsFontFamily) },
                leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone Icon") },
                modifier = Modifier.fillMaxWidth(0.8f),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Email field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email", fontFamily = poppinsFontFamily) },
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email Icon") },
                modifier = Modifier.fillMaxWidth(0.8f),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Password field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password", fontFamily = poppinsFontFamily) },
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Password Icon") },
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Confirm Password field
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(text = "Confirm Password", fontFamily = poppinsFontFamily) },
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Confirm Password Icon") },
                modifier = Modifier.fillMaxWidth(0.8f),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(1.dp))

            // Terms and Conditions checkbox and dialog
            TermsAndConditions(isChecked) { newValue -> isChecked = newValue }

            Spacer(modifier = Modifier.height(6.dp))

            // Sign Up button with validation
            Button(
                onClick = {
                    Log.i("SignUp", "User Registered: $email")
                    navController.navigate("LoginActivity")
                },
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1d7151)),
                enabled = firstName.isNotBlank() && lastName.isNotBlank() &&
                        userName.isNotBlank() && contactNumber.isNotBlank() &&
                        birthday.isNotBlank() && email.isNotBlank() &&
                        password.isNotBlank() && confirmPassword.isNotBlank() &&
                        password == confirmPassword && isChecked
            ) {
                Text(text = "Sign up", fontFamily = poppinsFontFamily)
            }

            // Login link
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

                TextButton(onClick = { navController.navigate("LoginActivity") }, enabled = true) {
                    Text(
                        text = "Log In",
                        color = Color(0xFF4557FF),
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

// Terms and Conditions composable with dialog
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsAndConditions(isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    var hasReachedBottom by remember { mutableStateOf(false) }

    // Terms list
    val terms = listOf(
        "1. You must be at least 18 years old to use Baskit and agree to provide accurate personal information when creating an account.",
        "2. You are responsible for maintaining the confidentiality of your login details.",
        "3. Orders placed through the app are subject to availability.",
        "4. Purchasing times are estimated and may vary due to unforeseen circumstances.",
        "5. Refunds, if applicable, will be processed according to Baskit’s refund policy.",
        "6. Baskit reserves the right to suspend or terminate accounts that violate these terms."
    )

    // Detect if user has scrolled to the bottom
    LaunchedEffect(scrollState.value, scrollState.maxValue) {
        if (scrollState.maxValue > 0) {
            hasReachedBottom = scrollState.value >= (scrollState.maxValue - 10)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp)
                .background(Color.White, shape = RoundedCornerShape(10.dp))
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {},
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF1d7151),
                    uncheckedColor = Color.Gray
                )
            )

            Text(
                text = " Agree to Terms and Conditions",
                fontSize = 12.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF4557FF)
            )

            IconButton(onClick = { showDialog = true }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand Terms",
                    modifier = Modifier.padding(end = 20.dp),
                    tint = Color(0xFF4557FF)
                )
            }
        }

        // Terms and Conditions dialog
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp, vertical = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Terms and Conditions",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = poppinsFontFamily,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 20.dp)
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        Text(
                            text = "Welcome to Baskit! These Terms and Conditions govern your use of our app and services.",
                            fontSize = 16.sp,
                            fontFamily = poppinsFontFamily,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 20.dp)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .verticalScroll(scrollState)
                                .background(Color(0xFFE0F4DE), shape = RoundedCornerShape(15.dp))
                                .padding(horizontal = 25.dp, vertical = 20.dp)
                        ) {
                            Spacer(modifier = Modifier.height(20.dp))
                            Image(
                                painter = painterResource(id = R.drawable.terms_img),
                                contentDescription = "Rules",
                                modifier = Modifier
                                    .height(170.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 10.dp, bottom = 40.dp)
                            )

                            Text(
                                text = "Terms",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = poppinsFontFamily,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            terms.forEach { term ->
                                TextItem(text = term)
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Decline Button
                            Button(
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(130.dp),
                                shape = RoundedCornerShape(10.dp),
                                onClick = {
                                    onCheckedChange(false)
                                    showDialog = false
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCB3B3B)),
                                enabled = hasReachedBottom
                            ) {
                                Text(
                                    text = "Decline",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = poppinsFontFamily
                                )
                            }

                            // Accept Button
                            Button(
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(130.dp),
                                shape = RoundedCornerShape(10.dp),
                                onClick = {
                                    onCheckedChange(true)
                                    showDialog = false
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1d7151)),
                                enabled = hasReachedBottom
                            ) {
                                Text(
                                    text = "Accept",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = poppinsFontFamily
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// Helper composable for rendering terms items
@Composable
fun TextItem(text: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = text,
            fontWeight = FontWeight.Normal,
            fontFamily = poppinsFontFamily,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}