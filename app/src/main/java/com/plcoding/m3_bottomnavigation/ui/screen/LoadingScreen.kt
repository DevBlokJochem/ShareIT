package com.plcoding.m3_bottomnavigation.ui.screen

import android.annotation.SuppressLint
import android.os.Handler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.plcoding.m3_bottomnavigation.BackgroundColor
import com.plcoding.m3_bottomnavigation.Screen
import com.plcoding.m3_bottomnavigation.data.User
import com.plcoding.m3_bottomnavigation.data.readConfigFromFile
import com.plcoding.m3_bottomnavigation.ui.DefaultViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingScreen(navController: NavController, defaultViewModel: DefaultViewModel) {

    val configFileName = "config.json"
    val context  = LocalContext.current
    val appConfig by remember { mutableStateOf(readConfigFromFile(context, configFileName) ?: User(null, null, null)) }

    Scaffold { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .BackgroundColor(defaultViewModel),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Even geduld aub")
            val handler = Handler()
            handler.postDelayed({
                if(appConfig.username == null || appConfig.email == null || appConfig.password == null) {
                    navController.navigate(Screen.RegisterScreen.route)
                }else{
                    navController.navigate(Screen.HomeScreen.route)
                }
            }, 5000)

        }
    }
}