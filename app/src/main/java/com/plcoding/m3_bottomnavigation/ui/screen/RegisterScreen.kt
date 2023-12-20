package com.plcoding.m3_bottomnavigation.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plcoding.m3_bottomnavigation.BackgroundColor
import com.plcoding.m3_bottomnavigation.LoadBottomNavigationTheme
import com.plcoding.m3_bottomnavigation.Screen
import com.plcoding.m3_bottomnavigation.data.User
import com.plcoding.m3_bottomnavigation.data.UserManager
import com.plcoding.m3_bottomnavigation.data.readConfigFromFile
import com.plcoding.m3_bottomnavigation.data.saveConfigToFile
import com.plcoding.m3_bottomnavigation.ui.DefaultViewModel
import com.plcoding.m3_bottomnavigation.utils.TextFieldWithHideKeyboardOnImeAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    defaultViewModel: DefaultViewModel,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {

    val configFileName = "config.json"
    val context  = LocalContext.current
    val appConfig by remember { mutableStateOf(readConfigFromFile(context, configFileName) ?: User(null, null, null)) }

    if(!(appConfig.username == null || appConfig.email == null || appConfig.password == null)) {
        val user = User(appConfig.username, appConfig.email, appConfig.password)
        UserManager.users.add(user)
        defaultViewModel.ownUser = user
        navController.navigate(Screen.HomeScreen.route)
    }else{
        Scaffold { _ ->
            Column(
                modifier = Modifier.BackgroundColor(defaultViewModel),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Gebruikersnaam: ${defaultViewModel.usernameData ?: "Vul een gebruikersnaam in"}")
                TextFieldWithHideKeyboardOnImeAction("Verander gebruikersnaam") {
                    defaultViewModel.setUsername(it)
                }

                Spacer(modifier = Modifier.height(50.dp))
                Text("Email: ${defaultViewModel.emailData ?: "Vul een email in"}")
                TextFieldWithHideKeyboardOnImeAction("Verander email") {
                    defaultViewModel.setEmail(it)
                }

                Spacer(modifier = Modifier.height(50.dp))
                Text("Wachtwoord: ${"*".repeat((defaultViewModel.passwordData ?: "").toString().length)}")
                TextFieldWithHideKeyboardOnImeAction("Verander wachtwoord") {
                    defaultViewModel.setPassword(it)
                }

                Button(
                    onClick = {
                        if (defaultViewModel.usernameData == null || defaultViewModel.emailData == null || defaultViewModel.passwordData == null) {
                            scope.launch {
                                snackbarHostState.showSnackbar("Je moet alle velden invullen")
                            }
                        }else{
                            val user = User(defaultViewModel.usernameData, defaultViewModel.emailData, defaultViewModel.passwordData)
                            UserManager.users.add(user)
                            defaultViewModel.ownUser = user
                            saveConfigToFile(context, configFileName, user)
                            navController.navigate(Screen.HomeScreen.route)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Opslaan")
                }
            }
        }
    }
}