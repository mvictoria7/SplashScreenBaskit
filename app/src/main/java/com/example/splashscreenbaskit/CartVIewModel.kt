package com.example.splashscreenbaskit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

data class CartItem(
    val name: String,
    val weight: String,
    var quantity: Int,
    val price: Double,
    val imageRes: Int
)

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems // Expose as an immutable list

    fun addToCart(item: CartItem) {
        // Find the existing item by name and weight
        val existingItem = _cartItems.find { it.name == item.name && it.weight == item.weight }

        if (existingItem != null) {
            // If the item exists, update its quantity
            existingItem.quantity += item.quantity
        } else {
            // Otherwise, add the new item to the cart
            _cartItems.add(item)
        }
    }

    fun removeFromCart(item: CartItem) {
        _cartItems.remove(item)
    }
}
