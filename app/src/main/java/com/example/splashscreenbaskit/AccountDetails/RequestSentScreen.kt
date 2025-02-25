package com.example.splashscreenbaskit.AccountDetails

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily

@Preview
@Composable
fun RequestPreview (){
    RequestSentScreen(navController = rememberNavController())
}
@Composable
fun RequestSentScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Page Indicator
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFF2E7D32), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "✔", color = Color.White, fontSize = 14.sp)
            }
            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .width(60.dp)
                    .padding(horizontal = 8.dp)
                    .height(4.dp)
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFF2E7D32), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "✔", color = Color.White, fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))


        Image(
            painter = painterResource(id = R.drawable.notif_img),
            contentDescription = "Request Sent Illustration",
            modifier = Modifier.size(250.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Confirmation Text
        Text("Request sent!", fontWeight = FontWeight.Bold, fontSize = 24.sp, fontFamily = poppinsFontFamily)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Your information is being verified.\nThis may take a few days. Please wait for updates to be sent through your email.",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(30.dp))

        // OK Button
        Button(
            onClick = { navController.navigate("HomeActivity") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("OK", color = Color.White)
        }
    }
}
