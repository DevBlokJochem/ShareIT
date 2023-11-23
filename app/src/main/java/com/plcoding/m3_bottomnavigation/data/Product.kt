package com.plcoding.m3_bottomnavigation.data

import java.util.UUID

data class Product(
    val id: UUID = UUID.randomUUID(),
    var name: String,
    var description: String,
    var photo: String?
)
