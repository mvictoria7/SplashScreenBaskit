package com.example.splashscreenbaskit.Products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
fun StoreScreenPreview() {
    StoreScreen(navController = rememberNavController())
}

@Composable
fun StoreScreen(navController: NavController) {
    var storeName by remember { mutableStateOf("") }
    var categoryInput by remember { mutableStateOf("") }
    val categories = remember { mutableStateOf(mutableListOf<String>()) }
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.seller_img),
                contentDescription = "Store Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(top = 70.dp, start = 40.dp)
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Display Store Name
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .background(Color.White)
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (storeName.isNotEmpty()) {
                    Text(
                        text = "$storeName's Store",
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Set Store Name Input (only shown if empty)
                if (storeName.isEmpty()) {
                    Row(
                        //modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        BasicTextField(
                            value = storeName,
                            onValueChange = { storeName = it },
                            modifier = Modifier.padding(end = 10.dp),
                            textStyle = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.Bold
                            ),
                            decorationBox = { innerTextField ->
                                Box {
                                    if (storeName.isEmpty()) {
                                        Text(
                                            text = "Set store name",
                                            color = Color.Black,
                                            fontSize = 18.sp,
                                            fontFamily = poppinsFontFamily,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.pencil),
                            contentDescription = "Edit",
                            tint = Color.Black,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

            }

            // Add Category Input and Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color(0xFFFFA52F))
                    .clickable { },
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 20.dp)
                ) {
                    BasicTextField(
                        value = categoryInput,
                        onValueChange = { categoryInput = it },
                        modifier = Modifier.padding(end = 5.dp),
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        ),
                        decorationBox = { innerTextField ->
                            Box {
                                if (categoryInput.isEmpty()) {
                                    Text(
                                        text = "Add a category",
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        fontFamily = poppinsFontFamily,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
                    IconButton(
                        onClick = {
                            if (categoryInput.isNotEmpty()) {
                                categories.value = categories.value.toMutableList().apply {
                                    add(categoryInput)
                                }
                                categoryInput = "" // Clear input after adding
                                selectedCategory =
                                    categoryInput // Set the first category as selected
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.list),
                            contentDescription = "Add Category",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            // Display Selected Category and Products
            selectedCategory?.let { category ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFA52F), RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    Text(
                        text = category,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Product Row (Example with one product and Add button)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Product Card
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(120.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.carrot),
                                contentDescription = "Product Image",
                                modifier = Modifier.size(60.dp)
                            )
                            Text(
                                text = "Carrot",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontFamily = poppinsFontFamily,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    // Add Product Card
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(120.dp),
                        shape = RoundedCornerShape(8.dp),
                        onClick = { /* Handle add product action */ }
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Product",
                                modifier = Modifier.size(40.dp),
                                tint = Color.Black
                            )
                            Text(
                                text = "Add a product",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontFamily = poppinsFontFamily,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 48.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}