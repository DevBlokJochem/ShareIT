package com.plcoding.m3_bottomnavigation.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.plcoding.m3_bottomnavigation.data.Product

class DefaultViewModel: ViewModel() {

    var backgroundColor by mutableStateOf(false)
    var notifications by mutableStateOf(false)
    var usernameData by mutableStateOf("NewUser")
    var emailData by mutableStateOf("newemail@gmail.com")
    var passwordData by mutableStateOf("secret123")
    val items by mutableStateOf(arrayListOf(
        Product(name = "name 1",
            description = "description 1 \n line 2",
            photo = null),
        Product(name = "name 2",
            description = "description 2 \n line 2",
            photo = null),
        Product(name = "name 3",
            description = "description 3 \n line 2",
            photo = null)))

    fun toggleBackgroundColor() {
        backgroundColor = !backgroundColor
    }
    fun toggleNotifications() {
        notifications = !notifications
    }

    fun setUsername(newName: String) {
        usernameData = newName
    }
    fun setEmail(newEmail: String) {
        emailData = newEmail
    }
    fun setPassword(newPassword: String) {
        passwordData = newPassword
    }

    fun updateItem(product: Product) {
        items.removeIf { prod -> prod.id == product.id }
        items.add(product)
    }

    fun getBackgroundColor(): Color {
        return if(backgroundColor) {
            Color(128,128,128)
        }else{
            Color(220, 220, 220)
        }
    }
    fun getNotication(): String {
        return if(notifications) {
            "aan"
        }else{
            "uit"
        }
    }
}