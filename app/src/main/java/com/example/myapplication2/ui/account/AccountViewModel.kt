package com.example.myapplication2.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is account Fragment"
    }
    val text: LiveData<String> = _text

    private val _darkmode = MutableLiveData<Boolean>().apply {
        value = false
    }
    val darkmode: LiveData<Boolean> = _darkmode

    fun setDarkmode(isDarkMode: Boolean) {
        _darkmode.value = isDarkMode
    }

    private val _username = MutableLiveData<String>().apply {
        value = "New User"
    }
    val username: LiveData<String> = _username
}