package com.plcoding.m3_bottomnavigation.ui.screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
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
            Text(
                text = "Home",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = defaultViewModel.getTextColor()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Divider(modifier = Modifier.fillMaxWidth(), color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "Jouw feed", color = defaultViewModel.getTextColor(), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())

            LazyRow {
                items(defaultViewModel.items) {
                    Box(
                        modifier = Modifier
                            .width(200.dp)
                            .padding(16.dp)
                            .clickable {
                                defaultViewModel.currentID = it.id
                                navController.navigate(Screen.ProductScreen.route)
                            }
                    ) {
                        ImageCard(bitmap = it.photo, title = it.name)
                    }
                }
            }
        }
    }
}