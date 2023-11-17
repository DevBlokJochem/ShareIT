package com.plcoding.m3_bottomnavigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plcoding.m3_bottomnavigation.ui.DefaultViewModel
import com.plcoding.m3_bottomnavigation.ui.screen.AccountScreen
import com.plcoding.m3_bottomnavigation.ui.screen.HomeScreen
import com.plcoding.m3_bottomnavigation.ui.screen.UploadScreen

@Composable
fun Navigation(defaultViewModel: DefaultViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController, defaultViewModel)
        }
        composable(route = Screen.UploadScreen.route) {
            UploadScreen(navController, defaultViewModel)
        }
        composable(route = Screen.AccountScreen.route) {
            AccountScreen(navController, defaultViewModel)
        }
    }
}