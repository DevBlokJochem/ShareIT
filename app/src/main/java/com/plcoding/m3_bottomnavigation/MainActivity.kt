package com.plcoding.m3_bottomnavigation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import com.plcoding.m3_bottomnavigation.data.User
import com.plcoding.m3_bottomnavigation.data.readConfigFromFile
import com.plcoding.m3_bottomnavigation.ui.DefaultViewModel

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<DefaultViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val configFileName = "config.json"
            val context  = LocalContext.current
            val appConfig by remember { mutableStateOf(readConfigFromFile(context, configFileName) ?: User(null, null, null)) }

            viewModel.usernameData = appConfig.username
            viewModel.emailData = appConfig.email
            viewModel.passwordData = appConfig.password

            Navigation(viewModel)
        }
    }
}