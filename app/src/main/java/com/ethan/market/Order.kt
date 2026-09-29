package com.ethan.market

data class Order (
    val orderId: String = "",
    val userId: String = "",
    val items: List<CartItem> = emptyList(),
    val total: Double = 0.0,
    val timestamp: Long = System.currentTimeMillis(),
    val status: String = "Pending",
)