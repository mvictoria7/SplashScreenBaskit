package com.example.splashscreenbaskit.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.splashscreenbaskit.Products.CartItem

class CartViewModel : ViewModel() {
    // Using mutableStateListOf to maintain the list of cart items
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    fun addToCart(item: CartItem) {
        _cartItems.add(item)
    }

    fun removeFromCart(item: CartItem) {
        _cartItems.remove(item)
    }
}
