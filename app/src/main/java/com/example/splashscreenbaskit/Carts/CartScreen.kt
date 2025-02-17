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
import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.splashscreenbaskit.viewmodel.CartViewModel
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily
import com.example.splashscreenbaskit.Products.CartItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@Composable
fun CartScreen(cartViewModel: CartViewModel, navController: NavController) {
    val cartItems = cartViewModel.cartItems

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
                    CartItemView(item, onRemoveItem = {
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

        Button(onClick = onRemoveItem) {
            Text(text = "Remove")
        }
    }
}
