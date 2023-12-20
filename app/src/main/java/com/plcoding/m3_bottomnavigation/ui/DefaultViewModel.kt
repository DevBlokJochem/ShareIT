package com.plcoding.m3_bottomnavigation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.runtime.Composable
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

    @SuppressLint("StaticFieldLeak")
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
    val user4 = UserManager.users[3]
    val user5 = UserManager.users[4]
    val user6 = UserManager.users[5]
    val user7 = UserManager.users[6]
    val user8 = UserManager.users[7]
    val user9 = UserManager.users[8]
    val user10 = UserManager.users[9]


    var ownUser: User? = null

    var items: ArrayList<Product> = ArrayList()
    var currentID: UUID? by mutableStateOf(null)
    var newID: UUID? by mutableStateOf(null)
    var newName: String? by mutableStateOf(null)
    var newDescription: String? by mutableStateOf(null)
    var newImageUri: Uri? by mutableStateOf(null)
    var newBitmap: Bitmap? by mutableStateOf(null)

    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun SetupItems() {
        val newItems by mutableStateOf(arrayListOf(
            createUser(user1, "Hamer", "Deze hamer is van zeer goede kwaliteit en kan je bij mij ophalen voor 5 euro per dag.", R.drawable.hamer),
            createUser(user2, "Ladder", "Een veiligere ladder dan dit kan je niet krijgen! Deze ladder is ook beschikbaar voor verzenden. Ook kan je hem ophalen voor 7 euro per dag.", R.drawable.ladder),
            createUser(user3, "Makita Boormachine", "Deze Makita boormachine is een absolute must-have voor iedereen die serieus bezig is met klussen. Met zijn krachtige prestaties en duurzame ontwerp is deze boormachine speciaal ontworpen voor professionals die streven naar precisie en efficiëntie. Je kan hem bij mij ophalen voor 20 euro per dag.", R.drawable.boormachine),))
        items = newItems
    }

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

@Composable
fun createUser(user: User, name: String, description: String, photo: Int): Product {
    return Product(UUID.randomUUID(), user, name, description, null, BitmapFactory.decodeResource(
        LocalContext.current.resources,
        photo,
        BitmapFactory.Options()
    ))
}