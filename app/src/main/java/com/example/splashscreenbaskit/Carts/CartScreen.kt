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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete

@Composable
fun CartScreen(cartViewModel: CartViewModel, navController: NavController) {
    val cartItems by remember { mutableStateOf(cartViewModel.cartItems) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
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

        // Make the list scrollable while keeping total and checkout fixed
        Box(modifier = Modifier.weight(1f)) {
            if (cartItems.isEmpty()) {
                Text(text = "Your basket is empty.", fontSize = 18.sp)
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
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

        // Fixed Total and Checkout Button
        val totalPrice = cartItems.sumOf { it.price * it.quantity }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Total: ₱${"%.2f".format(totalPrice)}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6CBF5F), // Green color
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { /* Checkout logic */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6CBF5F)),
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text(text = "Checkout", color = Color.White, fontSize = 20.sp)
            }
        }
    }
}


@Composable
fun CartItemView(item: CartItem, onRemoveItem: () -> Unit, onIncreaseQuantity: () -> Unit, onDecreaseQuantity: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.apple), // Placeholder image
                    contentDescription = "Product Image",
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = item.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(text = "${item.weight} x ${item.quantity}", fontSize = 16.sp)
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
