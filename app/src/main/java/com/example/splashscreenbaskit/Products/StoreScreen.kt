package com.example.splashscreenbaskit.Products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StoreScreenPreview(){
    StoreScreen(navController = rememberNavController())
}
@Composable
fun StoreScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(315.dp)
                .background(Color.White),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.seller_img),
                contentDescription = "Apple",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp),
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = { },
                modifier = Modifier
                    .padding(top = 50.dp, start = 16.dp)
                    .align(Alignment.TopStart)
                    .size(40.dp)
                    .background(Color.White, shape = CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier.size(15.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
                .background(Color(0xFFFFA52F)),
            contentAlignment = Alignment.TopCenter
        ) {
            //CategoryRow(selectedCategory, navController)
        }
    }
}

@Composable
fun CategoryRow(selectedCategory: MutableState<String?>, navController: NavController) {
    val categories = listOf("SHOP", "Vegetables", "Fruits", "Meats", "Fish", "Spices", "Frozen Foods")

    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(top = 5.dp, start = 8.dp, end = 10.dp)
    ) {
        items(categories) { category ->
            TextButton(
                modifier = Modifier
                    .background(
                        color = if (selectedCategory.value == category) Color(0xAAFFFFFF) else Color.Transparent,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .wrapContentHeight()
                    .heightIn(min = 20.dp),
                onClick = { selectedCategory.value = category }
            ) {
                Text(
                    text = category,
                    color = if (selectedCategory.value == category) Color(0xFFFFFFFF) else Color(0xFFFFFFFF),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppinsFontFamily,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}