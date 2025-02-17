package com.example.splashscreenbaskit.Carts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splashscreenbaskit.viewmodel.CartViewModel
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.font.FontWeight
import com.example.splashscreenbaskit.Products.CartItem

@Composable
fun CartScreen(cartViewModel: CartViewModel) {
    val cartItems = cartViewModel.cartItems

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Back button
        Spacer(modifier = Modifier.height(10.dp))

        // Center "My Basket" title
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
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
                    CartItemView(item, onRemoveItem = {
                        // Remove item from cart via the ViewModel
                        cartViewModel.removeFromCart(item)
                    })
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
fun CartItemView(item: CartItem, onRemoveItem: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = "${item.name} - ${item.weight} x ${item.quantity}",
            fontSize = 18.sp,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "₱${"%.2f".format(item.price * item.quantity)}",
            fontSize = 18.sp
        )
        Button(onClick = onRemoveItem) {
            Text(text = "Remove")
        }
    }
}
