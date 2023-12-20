package com.plcoding.m3_bottomnavigation.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plcoding.m3_bottomnavigation.BackgroundColor
import com.plcoding.m3_bottomnavigation.LoadBottomNavigationTheme
import com.plcoding.m3_bottomnavigation.Screen
import com.plcoding.m3_bottomnavigation.data.User
import com.plcoding.m3_bottomnavigation.data.UserManager
import com.plcoding.m3_bottomnavigation.data.saveConfigToFile
import com.plcoding.m3_bottomnavigation.ui.DefaultViewModel
import com.plcoding.m3_bottomnavigation.utils.TextFieldWithHideKeyboardOnImeAction

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AccountScreen(navController: NavController, defaultViewModel: DefaultViewModel) {

    val configFileName = "config.json"
    val context = LocalContext.current

    Scaffold(
        bottomBar = { LoadBottomNavigationTheme(navController, Screen.AccountScreen) }
    ) { _ ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .BackgroundColor(defaultViewModel)
                .padding(16.dp)
        ) {
            LazyColumn {
                item {
                    Text(
                        text = "Account",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        textAlign = TextAlign.Center,
                        color = defaultViewModel.getTextColor()
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Divider(modifier = Modifier.fillMaxWidth(), color = Color.Gray)
                    Spacer(modifier = Modifier.height(4.dp))
                }

                item {
                    Row(
                        modifier = Modifier
                            .clickable {
                                defaultViewModel.toggleBackgroundColor()
                            }
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Dark Mode",
                            color = defaultViewModel.getTextColor(),
                            modifier = Modifier.weight(1f)
                        )
                        Switch(
                            checked = defaultViewModel.backgroundColor,
                            onCheckedChange = {
                                defaultViewModel.toggleBackgroundColor()
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.Gray,
                                checkedTrackColor = Color.LightGray
                            )
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .clickable {
                                defaultViewModel.toggleNotifications()
                            }
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Meldingen ${defaultViewModel.getNotication()}",
                            color = defaultViewModel.getTextColor(),
                            modifier = Modifier.weight(1f)
                        )
                        Switch(
                            checked = defaultViewModel.notifications,
                            onCheckedChange = {
                                defaultViewModel.toggleNotifications()
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.Gray,
                                checkedTrackColor = Color.LightGray
                            )
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = "Gebruikersnaam: ${defaultViewModel.usernameData ?: "Vul een gebruikersnaam in"}",
                        color = defaultViewModel.getTextColor()
                    )
                    TextFieldWithHideKeyboardOnImeAction("Verander gebruikersnaam", defaultViewModel) {
                        defaultViewModel.setUsername(it)
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = "Email: ${defaultViewModel.emailData ?: "Vul een email in"}",
                        color = defaultViewModel.getTextColor()
                    )
                    TextFieldWithHideKeyboardOnImeAction("Verander email", defaultViewModel) {
                        defaultViewModel.setEmail(it)
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = "Wachtwoord: ${"*".repeat((defaultViewModel.passwordData ?: "").toString().length)}",
                        color = defaultViewModel.getTextColor()
                    )
                    TextFieldWithHideKeyboardOnImeAction("Verander wachtwoord", defaultViewModel) {
                        defaultViewModel.setPassword(it)
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(50.dp))
                    Button(
                        onClick = {
                            with(defaultViewModel) {
                                backgroundColor = false
                                notifications = false
                                usernameData = null
                                emailData = null
                                passwordData = null
                            }
                            UserManager.users.remove(defaultViewModel.ownUser)
                            defaultViewModel.ownUser = null
                            saveConfigToFile(context, configFileName, User(null, null, null))
                            navController.navigate(Screen.RegisterScreen.route)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Verwijder account")
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}