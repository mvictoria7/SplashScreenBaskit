package com.example.splashscreenbaskit.LoginSignup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(navController: NavController) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        // Background Image
        Image(
            painter = painterResource(id = R.drawable.blur_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            TextButton(
                onClick = {
                    coroutineScope.launch { pagerState.animateScrollToPage(2) }
                },
                modifier = Modifier
                    .zIndex(1f)
            ) {
                Text(
                    text = "Skip",
                    fontFamily = poppinsFontFamily,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }

        HorizontalPager(
            count = 3,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp)
        ) { page ->
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(150.dp))

                OnboardingImage(page)

                Spacer(modifier = Modifier.height(30.dp))

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
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PageIndicator(currentPage = pagerState.currentPage, totalScreens = 3)
        }

            Spacer(modifier = Modifier.height(16.dp))

            if (pagerState.currentPage == 2) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 20.dp, bottom = 30.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    TextButton(
                        onClick = { navController.navigate("LoginActivity") }
                    ) {
                        Text(
                            text = "Get Started",
                            fontFamily = poppinsFontFamily,
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
fun OnboardingImage(page: Int) {
    val imageRes = when (page) {
        0 -> R.drawable.onboarding_img1
        1 -> R.drawable.onboarding_img2
        else -> R.drawable.onboarding_img3
    }

    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth().height(310.dp)
    )
}

@Composable
fun OnboardingContent(title: String, description: String) {
    Spacer(modifier = Modifier.height(65.dp))

    Column(
        modifier = Modifier.padding(start = 50.dp, end = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontFamily = poppinsFontFamily,
            style = TextStyle(
                fontSize = 32.sp,
                lineHeight = 35.sp
            ),
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(21.dp))

        Text(
            text = description,
            fontFamily = poppinsFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
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
                    .background(
                        color = if (index == currentPage) Color(0xFF3F79FB) else Color(0xFFD9D9D9),
                        shape = CircleShape
                    )
            )
            if (index < totalScreens - 1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    OnboardingScreen(navController = rememberNavController())
}
