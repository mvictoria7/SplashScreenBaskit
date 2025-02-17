package com.example.splashscreenbaskit.Carts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.splashscreenbaskit.viewmodel.CartViewModel
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.splashscreenbaskit.R

@Composable
fun CartScreen(cartViewModel: CartViewModel, navController: NavController) {
    val cartItems by remember { mutableStateOf(cartViewModel.cartItems) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Back button and title
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "My Basket",
                fontSize = 24.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (cartItems.isEmpty()) {
            Text(text = "Your basket is empty.", fontSize = 18.sp)
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

        Spacer(modifier = Modifier.weight(1f))

        val totalPrice = cartItems.sumOf { it.price * it.quantity }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total: ₱${"%.2f".format(totalPrice)}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Button(onClick = { /* Checkout logic */ }) {
                Text(text = "Checkout")
            }
        }
    }
}

@Composable
fun CartItemView(item: CartItem, onRemoveItem: () -> Unit, onIncreaseQuantity: () -> Unit, onDecreaseQuantity: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${item.weight} x ${item.quantity}",
                fontSize = 16.sp
            )
        }

        Text(
            text = "₱${"%.2f".format(item.price * item.quantity)}",
            fontSize = 18.sp,
            modifier = Modifier.padding(end = 8.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            // Minus button
            IconButton(onClick = onDecreaseQuantity) {
                Icon(
                    painter = painterResource(id = R.drawable.minus),
                    contentDescription = "Decrease Quantity",
                    tint = Color.Black,
                    modifier = Modifier.size(15.dp)
                )
            }

            // Quantity text
            Text(
                text = item.quantity.toString(),
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            // Plus button
            IconButton(onClick = onIncreaseQuantity) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "Increase Quantity",
                    tint = Color.Black,
                    modifier = Modifier.size(15.dp)
                )
            }

            // Delete button
            IconButton(onClick = onRemoveItem) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Item",
                    tint = Color.Red
                )
            }
        }
    }
}
