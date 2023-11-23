package com.plcoding.m3_bottomnavigation.data

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.google.gson.GsonBuilder

data class User(
    val username: String?,
    val email: String?,
    val password: String?
)

// Functie om configuratiegegevens op te slaan in een bestand
fun saveConfigToFile(context: Context, fileName: String, config: User) {
    val jsonString = GsonBuilder().setPrettyPrinting().create()!!.toJson(config)
    context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
        it.write(jsonString.toByteArray())
    }
}

// Functie om configuratiegegevens uit een bestand te lezen
fun readConfigFromFile(context: Context, fileName: String): User? {
    return try {
        val jsonString = context.openFileInput(fileName).bufferedReader().use { it.readText() }
        GsonBuilder().setPrettyPrinting().create()!!.fromJson(jsonString, User::class.java)!!
    } catch (e: Exception) {
        null
    }
}