package com.example.splashscreenbaskit.Carts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.viewmodel.CartViewModel
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.splashscreenbaskit.viewmodel.CartItem

@Composable
fun CartScreen(cartViewModel: CartViewModel = viewModel()) {
    // Get the cart items state from the ViewModel
    val cartItems = cartViewModel.cartItems // Directly using cartItems as it's now a List

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "My Basket",
            fontSize = 24.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (cartItems.isEmpty()) {
            Text(text = "Your basket is empty.", fontSize = 18.sp)
        } else {
            Column {
                cartItems.forEach { item ->
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
fun CartItemView(item: CartItem, onRemoveItem: (CartItem) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = "₱${"%.2f".format(item.price)}", fontSize = 16.sp, color = Color.Gray)
                Text(text = "Qty: ${item.quantity}", fontSize = 16.sp)
            }

            IconButton(onClick = { onRemoveItem(item) }) {
                Icon(painter = painterResource(id = R.drawable.trash), contentDescription = "Remove")
            }
        }
    }
}
