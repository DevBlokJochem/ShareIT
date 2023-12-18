package com.plcoding.m3_bottomnavigation.data

import android.graphics.Bitmap
import android.net.Uri
import java.util.UUID

data class Product(
    val id: UUID,
    val owner: User,
    var name: String,
    var description: String,
    var imageUri: Uri?,
    var photo: Bitmap?
)