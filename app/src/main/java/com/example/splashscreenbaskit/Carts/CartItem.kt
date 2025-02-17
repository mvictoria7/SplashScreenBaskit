package com.example.splashscreenbaskit.Carts  // Make sure this matches your folder structure

data class CartItem(
    val name: String,
    val weight: String,
    var quantity: Int,
    var price: Double,
    val imageRes: Int
)