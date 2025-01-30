package com.example.splashscreenbaskit

import android.content.Intent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val pagerState = rememberPagerState(initialPage = 0)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HorizontalPager(
            count = 3,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Column(modifier = Modifier.fillMaxSize()) {
                WavyHeader(page = page, pagerState = pagerState)

                Spacer(modifier = Modifier.height(16.dp))

                OnboardingContent(
                    title = when (page) {
                        0 -> "Good Day!"
                        1 -> "Shop Smarter,\nNot Harder"
                        else -> "Welcome!"
                    },
                    description = when (page) {
                        0 -> "At Baskit, we believe that shopping should be simple and stress-free. Start by creating a personalized grocery list tailored to your needs."
                        1 -> "Why spend extra time in the market? Baskit lets you shop smarter by generating a code for effortless pickup, saving you both time and effort."
                        else -> "Convenience is at the heart of Baskit. Present your code in-store, collect your items with ease, and enjoy a seamless shopping experience."
                    }
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

            if (pagerState.currentPage == 2) {
                Button(
                    onClick = {navController.navigate("SignUpActivity")},
                    modifier = Modifier.height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD9D9D9))
                ) {
                    Text(
                        text = "Get Started",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun PageIndicator(currentPage: Int, totalScreens: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = 8.dp, bottom = 20.dp)
            .fillMaxWidth()
    ) {
        repeat(totalScreens) { index ->
            Box(
                modifier = Modifier
                    .size(10.dp)
                    //.padding(horizontal = 4.dp)
                    .background(
                        color = if (index == currentPage) Color(0xAAFF9100) else Color.Gray,
                        shape = CircleShape
                    )
            )
            if (index < totalScreens - 1) {
                Spacer(modifier = Modifier.width(8.dp)) // Adjust the width to set space between circles
            }
        }
    }
}

@Composable
fun WavyHeader(page: Int, pagerState: PagerState) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(550.dp)
    ) {
        // Interpolating between pages for smooth transitions
        val offset = pagerState.currentPageOffset

        val gradient = Brush.verticalGradient(
            colors = when (page) {
                0 -> listOf(Color(0xFF1d7151), Color(0xFF34a853))
                1 -> listOf(Color(0xFF34a853), Color(0xFF1d7151))
                else -> listOf(Color(0xFF1d7151), Color(0xFF34a853))
            }
        )

        // Starting and ending heights for the wave based on page transitions
        val startHeight = size.height * 0.8f
        val controlHeight1 = size.height * (0.5f - offset * 0.3f)
        val controlHeight2 = size.height * (0.9f + offset * 0.2f)
        val endHeight = size.height * 0.8f

        val path = Path().apply {
            moveTo(0f, startHeight) // Starting point of the wave
            cubicTo(
                size.width * 0.25f, controlHeight1, // First control point
                size.width * 0.75f, controlHeight2, // Second control point
                size.width, endHeight // Ending point
            )
            lineTo(size.width, 0f) // Closing the top of the canvas
            lineTo(0f, 0f)
            close()
        }

        drawPath(
            path = path,
            brush = gradient
        )
    }
}

@Composable
fun OnboardingContent(title: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = description,
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}