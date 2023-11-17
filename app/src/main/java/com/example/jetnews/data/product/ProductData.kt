package com.example.jetnews.data.product

import java.util.UUID

object ProductData {

    val products: ArrayList<Product> = arrayListOf(Product(UUID.randomUUID(), "test1", null, "", " ", 50.0, "ema lkml"), Product(UUID.randomUUID(), "test2", null, "", " ", 50.0, "ema lkml"), )

}

data class Product(
    val id: UUID,
    var name: String,
    var photo: String?,
    var description: String,
    val location: String,
    var price: Double,
    val seller: String
)