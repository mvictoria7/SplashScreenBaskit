package com.example.splashscreenbaskit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.splashscreenbaskit.Carts.CartItem

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    fun addToCart(item: CartItem) {
        val existingItem = _cartItems.find { it.name == item.name && it.weight == item.weight }
        if (existingItem != null) {
            existingItem.quantity += item.quantity
            existingItem.price += item.price * item.quantity
        } else {
            _cartItems.add(item)
        }
    }

    fun removeFromCart(item: CartItem) {
        _cartItems.remove(item)
    }

    fun increaseQuantity(item: CartItem) {
        val index = _cartItems.indexOfFirst { it.id == item.id }
        if (index != -1) {
            _cartItems[index] = item.copy(quantity = item.quantity + 1)
        }
    }

    fun decreaseQuantity(item: CartItem) {
        val index = _cartItems.indexOfFirst { it.id == item.id }
        if (index != -1 && item.quantity > 1) {
            _cartItems[index] = item.copy(quantity = item.quantity - 1)
        }
    }
}
