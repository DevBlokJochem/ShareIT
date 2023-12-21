package com.plcoding.m3_bottomnavigation.ui.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.plcoding.m3_bottomnavigation.ui.DefaultViewModel
import com.plcoding.m3_bottomnavigation.utils.PickImageFromGallery
import com.plcoding.m3_bottomnavigation.utils.TextFieldWithHideKeyboardOnImeAction
import com.plcoding.m3_bottomnavigation.utils.TwoOptionsButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadScreen(
    navController: NavController,
    defaultViewModel: DefaultViewModel
) {
    val context = LocalContext.current

    Scaffold(
        bottomBar = { LoadBottomNavigationTheme(navController, Screen.UploadScreen) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .BackgroundColor(defaultViewModel)
                .padding(16.dp)
        ) {
            LazyColumn {
                item {
                    Text(
                        text = "Maak een product",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = defaultViewModel.getTextColor()
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Divider(modifier = Modifier.fillMaxWidth(), color = Color.Gray)
                    Spacer(modifier = Modifier.height(10.dp))
                }

                item {
                    PickImageFromGallery(defaultViewModel = defaultViewModel)
                }

                item {
                    Spacer(modifier = Modifier.height(35.dp))
                    Text(text = "Naam: ${defaultViewModel.newName ?: "vul een naam in"}", color = defaultViewModel.getTextColor())
                    TextFieldWithHideKeyboardOnImeAction("Verander de naam", defaultViewModel) {
                        defaultViewModel.newName = it
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(35.dp))
                    Text(text = "Beschrijving: ${defaultViewModel.newDescription ?: "vul een beschrijving in"}", color = defaultViewModel.getTextColor())
                    TextFieldWithHideKeyboardOnImeAction("Verander de beschrijving", defaultViewModel) {
                        defaultViewModel.newDescription = it
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    TwoOptionsButton {
                        if (it == "Verhuren") {
                            defaultViewModel.newRequest = false
                        } else if (it == "Aanvragen") {
                            defaultViewModel.newRequest = true
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            if (defaultViewModel.newName == null) {
                                Toast.makeText(context, "Je moet een naam meegeven.", Toast.LENGTH_SHORT).show()
                            } else if (defaultViewModel.newDescription == null) {
                                Toast.makeText(context, "Je moet een beschrijving meegeven.", Toast.LENGTH_SHORT).show()
                            } else if (defaultViewModel.newRequest == null) {
                                Toast.makeText(context, "Je moet tussen verhuren en aanvragen kiezen.", Toast.LENGTH_SHORT).show()
                            } else {
                                defaultViewModel.updateItem()
                                defaultViewModel.newID = null
                                defaultViewModel.newName = null
                                defaultViewModel.newImageUri = null
                                defaultViewModel.newDescription = null
                                defaultViewModel.newBitmap = null
                                defaultViewModel.newRequest = false
                                navController.navigate(Screen.HomeScreen.route)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Opslaan", color = defaultViewModel.getTextColor())
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}