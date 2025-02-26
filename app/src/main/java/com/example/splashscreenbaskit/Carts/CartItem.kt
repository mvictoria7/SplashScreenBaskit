package com.example.splashscreenbaskit.Carts

data class CartItem(
    val id: Int,            // Unique ID for each item
    val name: String,
    val weight: String,
    var price: Double,
    var quantity: Int,      // Quantity is mutable to update it later
    val imageResId: Int, // Resource ID for the image
)
