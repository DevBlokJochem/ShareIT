package com.plcoding.m3_bottomnavigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plcoding.m3_bottomnavigation.ui.DefaultViewModel
import com.plcoding.m3_bottomnavigation.ui.screen.AccountScreen
import com.plcoding.m3_bottomnavigation.ui.screen.HomeScreen
import com.plcoding.m3_bottomnavigation.ui.screen.ProductScreen
import com.plcoding.m3_bottomnavigation.ui.screen.RegisterScreen
import com.plcoding.m3_bottomnavigation.ui.screen.UploadScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(defaultViewModel: DefaultViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.RegisterScreen.route) {
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(navController, defaultViewModel)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController, defaultViewModel)
        }
        composable(route = Screen.UploadScreen.route) {
            UploadScreen(navController, defaultViewModel)
        }
        composable(route = Screen.AccountScreen.route) {
            AccountScreen(navController, defaultViewModel)
        }
        composable(route = Screen.ProductScreen.route) {
            ProductScreen(navController, defaultViewModel)
        }
    }
}