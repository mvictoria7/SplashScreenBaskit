package com.example.splashscreenbaskit.Carts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.splashscreenbaskit.viewmodel.CartViewModel
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CartScreenPreview() {
    val navController = rememberNavController()
    val cartViewModel = CartViewModel()
    CartScreen(cartViewModel = cartViewModel, navController)
}
@Composable
fun CartScreen(cartViewModel: CartViewModel, navController: NavController) {
    val cartItems by remember { mutableStateOf(cartViewModel.cartItems) }

    Column(modifier = Modifier.fillMaxSize()
    ) {
        // Title Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp, start = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(90.dp))
            Text(
                text = "My Basket",
                fontSize = 24.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Product List inside padded Column
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .weight(1f)
        ) {
            if (cartItems.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.noorders_img),
                        contentDescription = "No Orders",
                        modifier = Modifier.size(220.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Your basket is empty!",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray,
                        fontFamily = poppinsFontFamily
                    )
                }
            } else {
                LazyColumn {
                    items(cartItems) { item ->
                        CartItemView(
                            item = item,
                            onRemoveItem = { cartViewModel.removeFromCart(item) },
                            onIncreaseQuantity = { cartViewModel.increaseQuantity(item) },
                            onDecreaseQuantity = { cartViewModel.decreaseQuantity(item) }
                        )
                    }
                }
            }
        }

        if (cartItems.isNotEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val totalPrice = cartItems.sumOf { it.price * it.quantity }
                val totalItems = cartItems.sumOf { it.quantity }

                Divider(
                    thickness = 0.5.dp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(25.dp))
                Row (
                    modifier = Modifier.fillMaxWidth() .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "Items:",
                        fontSize = 16.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                    Text(
                        text = "$totalItems",
                        fontSize = 16.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                Row (
                    modifier = Modifier.fillMaxWidth() .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "Total:",
                        fontSize = 24.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF83BD70)
                    )
                    Text(
                        text = "₱${"%.2f".format(totalPrice)}",
                        fontSize = 24.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF83BD70)
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = { navController.navigate("CheckoutScreen") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF83BD70)),
                    modifier = Modifier.width(205.dp) .height(58.dp)
                ) {
                    Text(text = "Checkout", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = poppinsFontFamily)
                }
            }
        }
    }
}

@Composable
fun CartItemView(
    item: CartItem,
    onRemoveItem: () -> Unit,
    onIncreaseQuantity: () -> Unit,
    onDecreaseQuantity: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = item.imageResId ?: R.drawable.noorders_img),
                    contentDescription = "Product Image",
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = item.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFontFamily)
                    Text(
                        text = "${item.weight ?: "N/A"}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppinsFontFamily,
                        color = Color(0xFF4D4D4D)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "₱${"%.2f".format(item.price)}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFontFamily,
                        color = Color(0xFF4D4D4D)
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(
                        onClick = onRemoveItem,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Item",
                            tint = Color.Red
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = onDecreaseQuantity) {
                            Icon(
                                painter = painterResource(id = R.drawable.minus),
                                contentDescription = "Decrease Quantity",
                                tint = Color.Black,
                                modifier = Modifier.size(15.dp)
                            )
                        }
                        Text(text = item.quantity.toString(), fontSize = 15.sp, fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold)

                        IconButton(onClick = onIncreaseQuantity) {
                            Icon(
                                painter = painterResource(id = R.drawable.add),
                                contentDescription = "Increase Quantity",
                                tint = Color.Black,
                                modifier = Modifier.size(15.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}