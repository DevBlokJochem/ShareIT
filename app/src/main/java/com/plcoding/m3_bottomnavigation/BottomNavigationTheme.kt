package com.plcoding.m3_bottomnavigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plcoding.m3_bottomnavigation.ui.DefaultViewModel

const val REGISTERTITLE = "Register"
const val HOMETITLE = "Home"
const val UPLOADTITLE = "Toevoegen"
const val ACCOUNTTITLE = "Account"
const val PRODUCTTITLE = "Product"

val NAVIGATIONITEMS = listOf(
    BottomNavigationItem(
        title = HOMETITLE,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    BottomNavigationItem(
        title = UPLOADTITLE,
        selectedIcon = Icons.Filled.Add,
        unselectedIcon = Icons.Outlined.Add
    ),
    BottomNavigationItem(
        title = ACCOUNTTITLE,
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    ),
)

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.BackgroundColor(viewModel: DefaultViewModel): Modifier {
    return Modifier.background(viewModel.getBackgroundColor()).fillMaxWidth().fillMaxHeight().padding(16.dp)
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoadBottomNavigationTheme(navController: NavController, selectedScreen: Screen) {
    NavigationBar {
        NAVIGATIONITEMS.forEachIndexed { _, item ->
            NavigationBarItem(
                selected = selectedScreen.route == item.title,
                onClick = {
                    navController.navigate(item.title)
                },
                label = {
                    Text(text = item.title)
                },
                alwaysShowLabel = false,
                icon = {
                    Box {
                        Icon(
                            imageVector = if (item.title == selectedScreen.route) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.title
                        )
                    }
                }
            )
        }
    }
}