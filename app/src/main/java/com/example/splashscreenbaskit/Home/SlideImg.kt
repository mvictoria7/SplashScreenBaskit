package com.example.splashscreenbaskit.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.splashscreenbaskit.R
import com.google.accompanist.pager.*

@Composable
fun SlideImg(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(initialPage = 0)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = 3,
            state = pagerState,
            modifier = Modifier.fillMaxSize() // Ensures full container width & height
        ) { page ->
            Image(
                painter = painterResource(
                    id = when (page) {
                        0 -> R.drawable.slider1
                        1 -> R.drawable.slider2
                        else -> R.drawable.slider3
                    }
                ),
                contentDescription = "Slider Image $page",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // Makes the image cover the entire container
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
