package com.example.splashscreenbaskit.AccountDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun EditStorePreview() {
    EditStore(navController = rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditStore(navController: NavController) {
    var storeName by remember { mutableStateOf("") }
    val categories = remember { mutableStateOf(mutableListOf<String>()) }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var expanded by remember { mutableStateOf(false) }
    val products = remember { mutableStateListOf<Product>() } // Changed to mutableStateListOf

    // Receive new product from AddProduct
    LaunchedEffect(Unit) {
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Product>("newProduct")?.observeForever { newProduct ->
            products.add(newProduct)
            navController.currentBackStackEntry?.savedStateHandle?.remove<Product>("newProduct")
        }
    }

    val predefinedCategories = listOf("Fruits", "Vegetables", "Meats", "Spices", "Frozen Foods", "Fish")
    val categoryOrder = listOf("Vegetables", "Fruits", "Meats", "Fish", "Spices", "Frozen Foods")

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
                    .height(100.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                        )
                    )
            )
            Text(
                "Aling Nena's Store",
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 10.dp),
                //.align(Alignment.BottomStart),
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontFamily = poppinsFontFamily)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .background(Color.White)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(vertical = 8.dp)
                        .menuAnchor()
                ) {
                    Text(
                        text = selectedCategory ?: "Add a category",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.add_category),
                        contentDescription = "Add Category",
                        tint = Color.Black,
                        modifier = Modifier.size(18.dp)
                    )
                }

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    predefinedCategories.forEach { category ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = category,
                                    style = TextStyle(
                                        fontFamily = poppinsFontFamily,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Normal,
                                        color = Color.Black
                                    )
                                )
                            },
                            onClick = {
                                if (!categories.value.contains(category)) {
                                    categories.value = categories.value.toMutableList().apply { add(category) }
                                    categories.value.sortBy { categoryOrder.indexOf(it) }
                                }
                                selectedCategory = category
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA52F)),
            modifier = Modifier
                .height(68.dp)
                .fillMaxWidth(),
            shape = RectangleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(categories.value) { category ->
                        Text(
                            text = category,
                            fontSize = 16.sp,
                            fontWeight = if (selectedCategory == category) FontWeight.ExtraBold else FontWeight.Normal,
                            fontFamily = poppinsFontFamily,
                            color = if (selectedCategory == category) Color.White else Color(0xFFCCCCCC),
                            modifier = Modifier
                                .clickable { selectedCategory = category }
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

        ProductGridWithAddButton(
            products = products.filter { it.category == selectedCategory || selectedCategory == null },
            navController = navController,
            modifier = Modifier
                .weight(1f)
                .padding(top = 10.dp)
        )
    }
}

@Composable
fun ProductGridWithAddButton(
    products: List<Product>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val productListWithAdd = remember(products) {
        products.toMutableList().apply {
            add(Product("Add a product", null)) // Placeholder for "Add" button
        }
    }

    Spacer(modifier = Modifier.height(10.dp))

    Column(modifier = modifier.fillMaxWidth()) {
        productListWithAdd.chunked(2).forEach { rowProducts ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowProducts.forEach { product ->
                    if (product.imageBitmap != null) {
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
                                    bitmap = product.imageBitmap!!.asImageBitmap(),
                                    contentDescription = "Product Image",
                                    modifier = Modifier
                                        .height(100.dp)
                                        .width(135.dp)
                                        .padding(top = 8.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = product.name,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    fontFamily = poppinsFontFamily,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )
                                Text(
                                    text = "₱${String.format("%.2f", product.price)}",
                                    fontSize = 14.sp,
                                    fontFamily = poppinsFontFamily,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                            }
                        }
                    } else {
                        Button(
                            onClick = { navController.navigate("AddProduct") },
                            modifier = Modifier
                                .weight(1f)
                                .height(170.dp)
                                .width(154.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF0F0F0),
                                contentColor = Color.White
                            ),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 4.dp,
                                pressedElevation = 8.dp
                            )
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.AddCircle,
                                    contentDescription = "Add Product",
                                    modifier = Modifier.size(35.dp),
                                    tint = Color.Black
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Add a product",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp,
                                    fontFamily = poppinsFontFamily,
                                    color = Color.Black
                                )
                            }
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