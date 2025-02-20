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
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CheckoutScreen(cartViewModel: CartViewModel, navController: NavController) {
    val cartItems by remember { mutableStateOf(cartViewModel.cartItems) }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column(modifier = Modifier.fillMaxSize().padding(bottom = 120.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Checkout",
                    fontSize = 24.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn {
                items(cartItems) { item ->
                    CheckoutItemView(item = item)
                }
            }
        }

        val totalPrice = cartItems.sumOf { it.price * it.quantity }
        val totalItems = cartItems.sumOf { it.quantity }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "ITEMS: $totalItems",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Total: ₱${"%.2f".format(totalPrice)}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6CBF5F),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { /* Payment Processing */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6CBF5F)),
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text(text = "Confirm Order", color = Color.White, fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun CheckoutItemView(item: CartItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = "${item.quantity}pcs", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
            Text(
                text = "₱${"%.2f".format(item.price * item.quantity)}",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6CBF5F),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}
