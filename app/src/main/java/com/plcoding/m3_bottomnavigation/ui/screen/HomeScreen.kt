package com.plcoding.m3_bottomnavigation.ui.screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plcoding.m3_bottomnavigation.BackgroundColor
import com.plcoding.m3_bottomnavigation.LoadBottomNavigationTheme
import com.plcoding.m3_bottomnavigation.Screen
import com.plcoding.m3_bottomnavigation.ui.DefaultViewModel
import com.plcoding.m3_bottomnavigation.utils.ImageCard

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, defaultViewModel: DefaultViewModel) {
    Scaffold(
        bottomBar = { LoadBottomNavigationTheme(navController, Screen.HomeScreen) }
    ) { _ ->
        Column(modifier = Modifier.BackgroundColor(defaultViewModel)) {
            Text(text ="Jouw feed",color = defaultViewModel.getTextColor()) // Updated text to "jouw feed"
            LazyRow { // Changed LazyColumn to LazyRow
                items(defaultViewModel.items) {
                    Box(modifier = Modifier
                        .width(200.dp) // Set a specific width for each item
                        .padding(16.dp)) {
                        ImageCard(bitmap = it.photo, title = it.name)
                    }
                }
            }
        }
    }
}