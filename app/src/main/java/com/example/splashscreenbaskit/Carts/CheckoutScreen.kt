package com.example.splashscreenbaskit.Carts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import com.example.splashscreenbaskit.R


@Composable
fun CheckoutScreen(cartViewModel: CartViewModel, navController: NavController) {
    val cartItems by remember { mutableStateOf(cartViewModel.cartItems) }
    var showCodeDialog by remember { mutableStateOf(false) } // State for Dialog

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Toolbar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
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

        // Products List with Padding
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp) // Products stay padded
                .weight(1f) // Allows scrolling
        ) {
            LazyColumn {
                items(cartItems) { item ->
                    CheckoutItemView(item = item)
                }
            }
        }

        // FULL-WIDTH SHADOW (End to End)
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Gray.copy(alpha = 0.5f), Color.Transparent)
                        )
                    )
            )
        }

        // Tagabili Column (Still inside padding)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        // Bottom Summary & Payment Section
        val totalPrice = cartItems.sumOf { it.price * it.quantity }


            Text(
                text = "Tagabili: Juan Sebastian",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "0900-000-0000",
                fontSize = 16.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(10.dp))
            Divider()

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Subtotal: ₱${"%.2f".format(totalPrice)}", fontSize = 16.sp, color = Color.Gray)
            Text(text = "Tagabili Fee: ₱0.00", fontSize = 16.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))

            Divider()

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Total: ₱${"%.2f".format(totalPrice)}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6CBF5F)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {showCodeDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1d7151)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "YOUR CODE", color = Color.White, fontSize = 20.sp)

            }
        }
    }

    // Show Dialog when button is clicked
    if (showCodeDialog) {
        YourCodeDialog(
            onDismiss = { showCodeDialog = false },
            onSaveImage = { /* Implement save as image function here */ }
        )
    }
}

// 📌 Styled Dialog Box Component (Matches Image Design)
@Composable
fun YourCodeDialog(onDismiss: () -> Unit, onSaveImage: () -> Unit) {
    val randomCode by remember { mutableStateOf(generateRandomCode()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)), // Dim background
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp) // Shadow effect
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Back Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onDismiss) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }

                Text(
                    text = "Present this code to\nour staff upon pickup.",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "YOUR CODE",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Code with Green Background
                Box(
                    modifier = Modifier
                        .background(color = Color(0xFF1d7151), shape = RoundedCornerShape(8.dp))
                        .padding(vertical = 12.dp, horizontal = 30.dp)
                ) {
                    Text(
                        text = randomCode,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Order Processing Text
                Text(
                    text = "Order processing...",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Save as Image Button
                Button(
                    onClick = { /* Handle image saving */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.save),
                            contentDescription = "Save as Image",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Save as Image", fontSize = 18.sp, color = Color.Black)
                    }
                }
            }
        }
    }
}

// 📌 Function to generate random code
fun generateRandomCode(): String {
    val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    return (1..8).map { chars.random() }.joinToString("")
}

// 📌 Checkout Item View
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
