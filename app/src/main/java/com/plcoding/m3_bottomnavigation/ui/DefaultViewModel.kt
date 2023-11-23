package com.plcoding.m3_bottomnavigation.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class DefaultViewModel: ViewModel() {

    var backgroundColor by mutableStateOf(false)
    var notifications by mutableStateOf(false)
    var usernameData by mutableStateOf("NewUser")
    var emailData by mutableStateOf("newemail@gmail.com")
    var passwordData by mutableStateOf("secret123")
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