package com.example.splashscreenbaskit.AccountDetails

import android.os.Bundle
import android.widget.Space
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.window.DialogProperties
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily
import kotlin.io.path.Path

@Preview(showBackground = true)
@Composable
fun AccountActivityPreview() {
    AccountActivity(navController = rememberNavController())
}

@Composable
fun AccountActivity(navController: NavController) {
    // State to control dialog visibility
    val showDialog = remember { mutableStateOf(false) }

    // Show dialog when the user clicks "Log Out"
    val onLogOutClick = {
        showDialog.value = true
    }

    // Handle "Yes" and "No" actions from the dialog
    val onConfirmLogOut = {
        // Navigate to the login screen
        navController.navigate("LoginActivity")
        showDialog.value = false
    }

    val onCancelLogOut = {
        // Just close the dialog without logging out
        showDialog.value = false
    }

    val scrollState = rememberScrollState()
    var showPopup by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            //.fillMaxWidth()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(
                onClick = {showPopup = true},
                shape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF5F5F5), contentColor = Color.Black),
                //elevation = ButtonDefaults.buttonElevation(20.dp),
                modifier = Modifier
                    .fillMaxWidth(0.38f)
                    .offset(x = (-10).dp)
                    .width(110.dp)
                    .height(35.dp)
            ) {
                if (showPopup) {
                    StartSellingPopup(
                        onCancel = { showPopup = false },
                        onProceed = {
                            showPopup = true
                            navController.navigate("ShopInformationScreen")
                        }
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.shop),
                    contentDescription = "Shop Icon",
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Start Selling",
                    fontFamily = poppinsFontFamily,
                    fontSize = 10.sp
                )

                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Notifications",
                    tint = Color.Black
                )
            }

            IconButton(
                onClick = {
                    navController.navigate("NotificationsActivity") },
                enabled = true,
                modifier = Modifier.padding(start = 130.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notifications",
                    tint = Color.Black
                )
            }

            IconButton(
                onClick = { navController.navigate("SettingsActivity") },
                enabled = true
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings",
                    tint = Color.Black
                )

            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Image(
            painter = painterResource(id = R.drawable.account_img),
            contentDescription = null,
            modifier = Modifier
                .height(177.dp)
                .padding( start = 20.dp)
                .offset(x = 75.dp,y = (100).dp)
        )
//Account details
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp, top = 300.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Account Details",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                fontFamily = poppinsFontFamily
                )

            Spacer(modifier = Modifier.height(20.dp))

            //username
            Column {

                Text(
                    text = "Name",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    fontFamily = poppinsFontFamily
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "")

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE0F4DE), RoundedCornerShape(10.dp))
                    ) {
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.padding(15.dp)
                        ){
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = Color.DarkGray
                            )
                            Text(text = "Maria Victoria Catabay",
                                fontFamily = poppinsFontFamily)
                        }
                    }

                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Username",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    fontFamily = poppinsFontFamily
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "")

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF0F6CE), RoundedCornerShape(10.dp))
                    ) {
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.padding(15.dp)
                        ){
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = Color.DarkGray
                            )
                            Text(text = "mariavicky",
                                fontFamily = poppinsFontFamily)
                        }
                    }

                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            //birthday
            Column {

                Text(
                    text = "Email",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    fontFamily = poppinsFontFamily

                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "")

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xBBC2E1ED), RoundedCornerShape(10.dp))

                    ) {
                        Row (verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.padding(15.dp)
                        ){
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = null,
                                tint = Color.DarkGray
                            )
                            Text(text = "test@gmail.com",
                                fontFamily = poppinsFontFamily
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            //email
            Column {

                Text(
                    text = "Contact Number",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    fontFamily = poppinsFontFamily
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "")

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE0F4DE), RoundedCornerShape(10.dp))
                    ) {
                        Row (verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.padding(15.dp)
                        ){
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = null,
                                tint = Color.DarkGray
                            )
                            Text(text = "0900-000-0000", fontFamily = poppinsFontFamily)

                            Spacer(modifier = Modifier.width(100.dp))

                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                tint = Color.DarkGray
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            //password
            Column {

                Text(
                    text = "Password",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    fontFamily = poppinsFontFamily
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "")

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF0F6CE), RoundedCornerShape(10.dp))
                    ) {
                        Row (verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.padding(15.dp)
                        ){
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null,
                                tint = Color.DarkGray
                            )
                            Text(text = "********")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            //reset password
            Column (horizontalAlignment = Alignment.End
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(top = 5.dp),
                    horizontalArrangement = Arrangement.Start
                ) {

                    Text(
                        text = "Reset password",
                        fontSize = 12.sp,
                        color = Color(0xFF4557FF),
                        fontFamily = poppinsFontFamily,
                        style = TextStyle(textDecoration = TextDecoration.Underline),
                        modifier = Modifier.clickable {
                            navController.navigate("ResetPasswordScreen")
                        }
                    )
                }
            }

            Column ( modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(modifier = Modifier.height(30.dp))

                // Log Out button
                Button(
                    onClick = onLogOutClick,
                    modifier = Modifier.fillMaxWidth() .height(47.dp),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color(0xFFE22727)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFFE22727))
                ) {
                    Text(text = "Log Out", fontWeight = FontWeight.Bold, fontFamily = poppinsFontFamily)
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }

    // Confirmation Dialog
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = {
                Text("Log Out?",
                    fontSize = 18.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold ) },

            text = { Text("Are you sure you want to log out?",
                fontSize = 14.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal) },


            confirmButton = {
                Button(
                    onClick = {
                        navController.navigate(route = "LoginActivity")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCB3B3B))
                ) {
                    Text("Log out",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold)
                }
            },
            dismissButton = {
                Button(
                    onClick = onCancelLogOut,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD9D9D9))
                ) {
                    Text("Cancel", color = Color.Black, fontSize = 15.sp, fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold)
                }
            },
            containerColor = Color.White,
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
            modifier = Modifier.size(width = 325.dp, height = 185.dp) .clip(RoundedCornerShape(20.dp))
        )

    }
}

@Composable
fun StartSellingPopup(onCancel: () -> Unit, onProceed: () -> Unit) {
    AlertDialog(
        onDismissRequest = {  },
        title = {
            Text(
                text = "Start Selling?",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                fontFamily = poppinsFontFamily,
                color = Color.Black
            )
        },
        text = {
            Text(
                "Create your own store and post your products now!",
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                fontFamily = poppinsFontFamily,
                color = Color.Black
            )
        },
        confirmButton = {
            Button(
                modifier = Modifier
                    .height(38.dp)
                    .width(104.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1D7151),
                    contentColor = Color.White
                ),
                onClick = onProceed
            ) {
                Text("Proceed",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    fontFamily = poppinsFontFamily,
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(modifier = Modifier
                .height(38.dp)
                .width(104.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD9D9D9),
                    contentColor = Color.White
                ),
                onClick = onCancel
            ) {
                Text("Cancel",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    fontFamily = poppinsFontFamily,
                    color = Color.Black
                )
            }
        },
        containerColor = Color.White,
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        modifier = Modifier.size(width = 325.dp, height = 190.dp) .clip(RoundedCornerShape(20.dp))
    )
}

class AccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccountActivity()
        }
    }
}

