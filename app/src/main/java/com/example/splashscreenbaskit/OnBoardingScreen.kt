package com.example.splashscreenbaskit

import android.content.Intent
import android.graphics.Paint.Align
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.*
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

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
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {

                WavyHeader(page = page, pagerState = pagerState)

                OnboardingContent(
                    title = when (page) {
                        0 -> "Welcome"
                        1 -> "Shop Smarter,\nNot Harder"
                        else -> "One-stop Pickup"
                    },
                    description = when (page) {
                        0 -> "Enjoy a fun experience\nof personalized market shopping!"
                        1 -> "Add items to your basket and let\nour Tagabili do the work for you"
                        else -> "Pick your orders up from\nour branches in one-go!"
                    }
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize() .padding(end = 20.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            PageIndicator(currentPage = pagerState.currentPage, totalScreens = 3)

            if (pagerState.currentPage == 2) {
                TextButton(
                    onClick = {navController.navigate("SignUpActivity")},
                    modifier = Modifier.padding(bottom = 30.dp)
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
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
        modifier = Modifier
            .padding(top = 8.dp, bottom = 80.dp)
            .fillMaxWidth()
    ) {
        repeat(totalScreens) { index ->
            Box(
                modifier = Modifier
                    .size(10.dp)
//                    .padding(horizontal = 4.dp)
                    .background(
                        color = if (index == currentPage) Color(0xFFFFA52F) else Color(0xFFD9D9D9),
                        shape = CircleShape
                    )
            )
            if (index < totalScreens - 1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun WavyHeader(page: Int, pagerState: PagerState) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        val offset = pagerState.currentPageOffset

        val gradient = Brush.verticalGradient(
            colors = when (page) {
                0 -> listOf(Color(0xFF34a853), Color(0xFF1d7151))
                1 -> listOf(Color(0xFF1d7151), Color(0xFF34a853))
                else -> listOf(Color(0xFF34a853), Color(0xFF1d7151))
            }
        )

        // Starting & ending heights for the wave
        val startHeight = size.height * 1f
        val controlHeight1 = size.height * (0.7f - offset * 0.3f)
        val controlHeight2 = size.height * (1f + offset * 0.2f)
        val endHeight = size.height * 1f

        val path = Path().apply {
            moveTo(0f, startHeight)
            cubicTo(
                size.width * 0.25f, controlHeight1,
                size.width * 0.75f, controlHeight2,
                size.width, endHeight
            )
            lineTo(size.width, 0f)
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

    Spacer(modifier = Modifier.height(80.dp))

    Column(
        modifier = Modifier.padding(start = 50.dp, end = 50.dp ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = description,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}