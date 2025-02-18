package com.example.splashscreenbaskit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.splashscreenbaskit.Carts.CartItem  // Import CartItem class from Carts package

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    // Add or update an item in the cart
    fun addToCart(item: CartItem) {
        val existingItem = _cartItems.find { it.name == item.name && it.weight == item.weight }

        if (existingItem != null) {
            // If the item exists, update its quantity and price
            existingItem.quantity += item.quantity
            existingItem.price += item.price * item.quantity  // Update price based on added quantity
        } else {
            // If it does not exist, add the item to the cart
            _cartItems.add(item)
        }
    }


    // Remove an item
    fun removeFromCart(item: CartItem) {
        _cartItems.remove(item)
    }

    // Increase the quantity
    fun increaseQuantity(item: CartItem) {
        val updatedItem = item.copy(quantity = item.quantity + 1)
        val updatedItems = _cartItems.map { if (it.id == item.id) updatedItem else it }
        _cartItems.clear()
        _cartItems.addAll(updatedItems)
    }

    // Decrease the quantity of an item in the cart
    fun decreaseQuantity(item: CartItem) {
        if (item.quantity > 1) {
            val updatedItem = item.copy(quantity = item.quantity - 1)
            val updatedItems = _cartItems.map { if (it.id == item.id) updatedItem else it }
            _cartItems.clear()
            _cartItems.addAll(updatedItems)
        }
    }
}
