package com.example.splashscreenbaskit.AccountDetails

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.splashscreenbaskit.Home.Product
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditStorePreview(){
    EditStore(navController = rememberNavController())
}
@Composable
fun  EditStore(navController: NavController) {

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
                .height(315.dp)
                .background(Color(0xFFDBDBDB)),
            contentAlignment = Alignment.BottomStart
        ) {

            Image(
                painter = painterResource(id = R.drawable.seller_img),
                contentDescription = "Seller",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(315.dp),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                            )
                        )
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.BottomStart)
            ) {
                TextButton(onClick = { }
                ) {
                    Text(
                        "Aling Lita's Store",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontFamily = poppinsFontFamily)
                    )
                }

                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }

            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(top = 70.dp, start = 40.dp)
                    .align(Alignment.TopStart)
                    .size(15.dp)
                    .background(Color(0xAAFFFFFF), shape = CircleShape)
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
                .height(56.dp)
                .background(Color.White)
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
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontFamily = poppinsFontFamily,
                                    fontWeight = FontWeight.SemiBold
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
                        tint = Color.Black,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

        }

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA52F)),
            modifier = Modifier
                .height(68.dp)
                .fillMaxWidth()
                .clickable {
                    // navController.navigate("ProductScreen/${product.name}")
                },
            shape = RectangleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth() .padding(start = 20.dp, end = 20.dp)
                ) {
                    Text(
                        text = "Vegetables",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = poppinsFontFamily,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = "Dropdown",
                        tint = Color.White,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }



        //temporary lang
        Row (modifier = Modifier.padding(30.dp) .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
                modifier = Modifier
                    .height(170.dp)
                    .width(154.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        //navController.navigate("ProductScreen/${product.name}")
                    },
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.apple),
                        contentDescription = "Product Image",
                        modifier = Modifier
                            .height(100.dp)
                            .width(135.dp)
                            .padding(top = 8.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Apple",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        fontFamily = poppinsFontFamily,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
                modifier = Modifier
                    .height(170.dp)
                    .width(154.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        //navController.navigate("ProductScreen/${product.name}")
                    },
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AddCircle,
                            contentDescription = "Back",
                            tint = Color.Black,
                            modifier = Modifier.size(35.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Add a product",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }

        }
    }
}

//idk how to implement the cards hehe
@Composable
fun ProductGrid(
    products: List<Product>, navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        products.chunked(2).forEach { rowProducts ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowProducts.forEach { product ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
                        modifier = Modifier
                            .weight(1f)
                            .height(170.dp)
                            .width(154.dp)
                            .padding(4.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                navController.navigate("ProductScreen/${product.name}")
                            },
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = product.imageRes),
                                contentDescription = "Product Image",
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(135.dp)
                                    .padding(top = 8.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = product.name,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                fontFamily = poppinsFontFamily,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
                if (rowProducts.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}