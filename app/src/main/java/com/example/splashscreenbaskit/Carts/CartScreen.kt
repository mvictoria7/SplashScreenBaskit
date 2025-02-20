package com.example.splashscreenbaskit.Carts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CartScreen(cartViewModel: CartViewModel, navController: NavController) {
    val cartItems by remember { mutableStateOf(cartViewModel.cartItems) }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column(modifier = Modifier.fillMaxSize().padding(bottom = 120.dp)) {
            // Title Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "My Basket",
                    fontSize = 24.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (cartItems.isEmpty()) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.noorders_img),
                        contentDescription = "No Orders",
                        modifier = Modifier.size(250.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Your basket is empty!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
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

        val totalPrice = cartItems.sumOf { it.price * it.quantity }
        val totalItems = cartItems.sumOf { it.quantity }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "ITEMS: $totalItems",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Total: ₱${"%.2f".format(totalPrice)}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6CBF5F),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { navController.navigate("CheckoutScreen") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6CBF5F)),
                modifier = Modifier.fillMaxWidth(0.9f).align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Checkout", color = Color.White, fontSize = 20.sp)
            }
        }
    }
}


@Composable
    fun CartItemView(item: CartItem, onRemoveItem: () -> Unit, onIncreaseQuantity: () -> Unit, onDecreaseQuantity: () -> Unit) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
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
                        painter = painterResource(id = item.imageResId),
                        contentDescription = "Product Image",
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))


                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = item.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text(text = "${item.quantity}pcs", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        Text(text = "₱${"%.2f".format(item.price)}", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6CBF5F))
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
                            Text(text = item.quantity.toString(), fontSize = 18.sp)
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

    //@Preview(showBackground = true)
    //@Composable
    //fun CartScreen (){
    //    CartScreen()
    //}
