package com.example.splashscreenbaskit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*

@Composable
fun Slide(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(initialPage = 0)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = 3,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = painterResource(
                    id = when (page) {
                        0 -> R.drawable.baskit_logo
                        1 -> R.drawable.testimg
                        else -> R.drawable.baskitlogo_white
                    }
                ),
                contentDescription = "Logo $page",
                modifier = Modifier.fillMaxSize()
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
