package com.example.splashscreenbaskit
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
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


@Preview(showBackground = true)
@Composable
fun OnBoardingScreen() {
    OnboardingScreen()
}



@Composable
fun OnboardingScreen() {
    var currentPage by remember { mutableStateOf(0) }
    val totalScreens = 3

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        WavyHeader()


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 550.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            PageIndicator(currentPage = currentPage, totalScreens = totalScreens)


            when (currentPage) {
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

            Spacer(modifier = Modifier.weight(1f))


            BottomNavigation(
                currentPage = currentPage,
                totalScreens = totalScreens,
                onNextClick = {
                    if (currentPage < totalScreens - 1) {
                        currentPage++
                    }
                },
                onGetStartedClick = {

                }
            )
        }
    }
}

@Composable
fun PageIndicator(currentPage: Int, totalScreens: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
    ) {
        repeat(totalScreens) { index ->
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .padding(horizontal = 2.dp)
                    .background(
                        color = if (index == currentPage) Color(0xFF00471B) else Color.Gray,
                        shape = CircleShape
                    ))
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
                size.width * 0.25f, size.height * 0.3f,
                size.width * 0.75f, size.height * 0.7f,
                size.width, size.height * 0.6f
            )
            lineTo(size.width, 0f)
            lineTo(0f, 0f)
            close()
        }
        clipPath(path) {
            drawRoundRect(
                color = Color(0xFF00471B),
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
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = description,
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BottomNavigation(
    currentPage: Int,
    totalScreens: Int,
    onNextClick: () -> Unit,
    onGetStartedClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            repeat(totalScreens) { index ->
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .padding(horizontal = 4.dp)
                        .background(
                            color = if (index == currentPage) Color(0xFFFFA726) else Color.Gray,
                            shape = CircleShape
                        )
                )
            }
        }


        Button(
            onClick = if (currentPage < totalScreens - 1) onNextClick else onGetStartedClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00471B))
        ) {
            Text(
                text = if (currentPage < totalScreens - 1) "Next" else "Get Started",
                color = Color.White
            )
        }
    }
}

