package com.plcoding.m3_bottomnavigation.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.plcoding.m3_bottomnavigation.LoadBottomNavigationTheme
import com.plcoding.m3_bottomnavigation.Screen
import com.plcoding.m3_bottomnavigation.data.UserManager
import com.plcoding.m3_bottomnavigation.ui.DefaultViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    navController: NavController,
    defaultViewModel: DefaultViewModel
) {
    val context = LocalContext.current

    if (defaultViewModel.currentID == null || defaultViewModel.items.firstOrNull { item -> item.id == defaultViewModel.currentID } == null) {
        navController.navigate(Screen.HomeScreen.route)
    } else {
        val product = defaultViewModel.items.first { item -> item.id == defaultViewModel.currentID }

        Scaffold(
            bottomBar = { LoadBottomNavigationTheme(navController, Screen.ProductScreen) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                if(product.photo == null) {
                    Icon(
                        imageVector = Icons.Rounded.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }else{
                    Image(
                        bitmap = product.photo!!.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = product.name,
                    color = defaultViewModel.getTextColor(),
                    fontSize = 20.sp // You can adjust the font size as needed
                )
                Spacer(modifier = Modifier.height(8.dp))
                val user = UserManager.users.firstOrNull { user -> user.email == product.owner.email }
                if(user != null) {
                    Text(text = "${user.username} - ${user.email}", color = defaultViewModel.getTextColor())
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = product.description,
                    color = defaultViewModel.getTextColor(),
                    fontSize = 16.sp // You can adjust the font size as needed
                )
            }
        }
    }
}