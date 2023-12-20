package com.plcoding.m3_bottomnavigation.data

import android.content.Context
import com.google.gson.GsonBuilder

data class User(
    var username: String?,
    var email: String?,
    var password: String?
)

object UserManager {

    val users: ArrayList<User> = arrayListOf(
        User("Piet Jansen", "Piet.Jansen@gmail.com", "wachtwoord123"),
        User("Anna de Vries", "Anna.deVries@gmail.com", "123456word"),
        User("Eva Bakker", "Eva.Bakker@gmail.com", "geheim987"),
        User("Jan van der Laan", "Jan.vanderLaan@gmail.com", "welkom456"),
        User("Lotte Visser", "Lotte.Visser@gmail.com", "hallo123xyz"),
        User("Tom de Boer", "Tom.deBoer@gmail.com", "qwerty789"),
        User("Sophie Molenaar", "Sophie.Molenaar@gmail.com", "test123abc"),
        User("Jasper Mulder", "Jasper.Mulder@gmail.com", "password456def"),
        User("Emma van Dijk", "Emma.vanDijk@gmail.com", "letmein789"),
        User("Luuk Hoekstra", "Luuk.Hoekstra@gmail.com", "admin123xyz")
    )

}

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