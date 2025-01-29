package com.example.splashscreenbaskit

import android.content.Intent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.*

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    OnboardingScreen(navController = rememberNavController())
}

@Composable
fun OnboardingScreen(navController: NavController) {
    val pagerState = rememberPagerState (initialPage = 0)

    val context = LocalContext.current

    Box(        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        WavyHeader()

        // Pager for Onboarding Screens
        HorizontalPager (
            count = 3,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> OnboardingContent(
                    title = "Good Day!",
                    description = "At Baskit, we believe that shopping should \nbe simple and stress-free. \nStart by creating a personalized grocery \nlist tailored to your needs."
                )
                1 -> OnboardingContent(
                    title = "Shop Smarter,\nNot Harder",
                    description = "Why spend extra time in the market? \nBaskit lets you shop smarter by generating a code \nfor effortless pickup, saving you both time and effort."
                )
                2 -> OnboardingContent(
                    title = "Welcome!",
                    description = "Convenience is at the heart of Baskit.\nPresent your code in-store, collect your items \nwith ease, and enjoy a seamless shopping experience."
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PageIndicator(currentPage = pagerState.currentPage, totalScreens = 3)

            // check if on the last page
            if (pagerState.currentPage == 2) {
                TextButton(onClick = {
                    // Navigate to SignUp Activity
                    navController.navigate("SignUpActivity")
                }) {
                    Text("Get Started", color = Color(0xFF1d7151), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun PageIndicator(currentPage: Int, totalScreens: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(top = 8.dp, bottom = 20.dp)
    ) {
        repeat(totalScreens) { index ->
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .padding(horizontal = 1.dp)
                    .background(
                        color = if (index == currentPage) Color(0xAAFF9100) else Color.Gray,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
fun WavyHeader() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(800.dp)
    ) {
        val path = Path().apply {
            moveTo(0f, size.height * 0.6f)
            cubicTo(
                size.width * 0.35f, size.height * 0.3f,
                size.width * 0.75f, size.height * 0.7f,
                size.width, size.height * 0.6f
            )
            lineTo(size.width, 0f)
            lineTo(0f, 0f)
            close()
        }
        clipPath(path) {
            drawRoundRect(
                color = Color(0xFF1d7151),
                size = size,
                cornerRadius = CornerRadius(0f, 0f)
            )
        }
    }
}

@Composable
fun OnboardingContent(title: String, description: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.offset(y = 150.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = description,
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.offset(y = 180.dp)
        )
    }
}
