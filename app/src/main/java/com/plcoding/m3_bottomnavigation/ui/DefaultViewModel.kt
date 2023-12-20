package com.plcoding.m3_bottomnavigation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.plcoding.m3_bottomnavigation.R
import com.plcoding.m3_bottomnavigation.data.Product
import com.plcoding.m3_bottomnavigation.data.User
import com.plcoding.m3_bottomnavigation.data.UserManager
import com.plcoding.m3_bottomnavigation.data.saveConfigToFile
import java.util.UUID

@SuppressLint("MutableCollectionMutableState")
class DefaultViewModel: ViewModel() {

    lateinit var context: Context
    lateinit var configFileName: String

    var backgroundColor by mutableStateOf(false)
    var notifications by mutableStateOf(false)
    var usernameData: String? by mutableStateOf(null)
    var emailData: String? by mutableStateOf(null)
    var passwordData: String? by mutableStateOf(null)

    val user1 = UserManager.users[0]
    val user2 = UserManager.users[1]
    val user3 = UserManager.users[2]

    var ownUser: User? = null

    val items by mutableStateOf(arrayListOf(
        Product(id = UUID.randomUUID(),
            owner = user1,
            name = "name 1",
            description = "description 1 \n line 2",
            imageUri = null,
            photo = Bitmap.createBitmap(R.drawable.)),
        Product(id = UUID.randomUUID(),
            owner = user2,
            name = "name 2",
            description = "description 2 \n line 2",
            imageUri = null,
            photo = null),
        Product(id = UUID.randomUUID(),
            owner = user3,
            name = "name 3",
            description = "description 3 \n line 2",
            imageUri = null,
            photo = null)))
    var currentID: UUID? by mutableStateOf(null)
    var newID: UUID? by mutableStateOf(null)
    var newName: String? by mutableStateOf(null)
    var newDescription: String? by mutableStateOf(null)
    var newImageUri: Uri? by mutableStateOf(null)
    var newBitmap: Bitmap? by mutableStateOf(null)

    fun toggleBackgroundColor() {
        backgroundColor = !backgroundColor
    }
    fun toggleNotifications() {
        notifications = !notifications
    }

    fun setUsername(newName: String) {
        usernameData = newName
        if(ownUser != null) {
            ownUser!!.username = newName
            saveConfigToFile(context, configFileName, ownUser!!)
        }
    }
    fun setEmail(newEmail: String) {
        emailData = newEmail
        if(ownUser != null) {
            ownUser!!.email = newEmail
            saveConfigToFile(context, configFileName, ownUser!!)
        }
    }
    fun setPassword(newPassword: String) {
        passwordData = newPassword
        if(ownUser != null) {
            ownUser!!.password = newPassword
            saveConfigToFile(context, configFileName, ownUser!!)
        }
    }

    fun updateItem() {
        items.removeIf { prod -> prod.id == newID }
        items.add(Product(
            newID ?: UUID.randomUUID(),
            owner = ownUser!!,
            newName ?: "no name",
            newDescription ?: "no description",
            newImageUri,
            newBitmap))
    }

    fun getBackgroundColor(): Color {
        return if(backgroundColor) {
            Color(50,50,50)
        }else{
            Color(220, 220, 220)
        }
    }

    fun getTextColor(): Color {
        return if(backgroundColor) {
            Color(200,200,200)
        }else{
            Color(1, 1, 1)
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