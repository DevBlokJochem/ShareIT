package com.plcoding.m3_bottomnavigation.ui.screen

import android.annotation.SuppressLint
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plcoding.m3_bottomnavigation.BackgroundColor
import com.plcoding.m3_bottomnavigation.LoadBottomNavigationTheme
import com.plcoding.m3_bottomnavigation.Screen
import com.plcoding.m3_bottomnavigation.ui.DefaultViewModel
import com.plcoding.m3_bottomnavigation.utils.PickImageFromGallery
import com.plcoding.m3_bottomnavigation.utils.TextFieldWithHideKeyboardOnImeAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadScreen(
    navController: NavController,
    defaultViewModel: DefaultViewModel,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {

    val context = LocalContext.current

    Scaffold(
        bottomBar = { LoadBottomNavigationTheme(navController, Screen.UploadScreen) }
    ) {
        Column(
            modifier = Modifier.BackgroundColor(defaultViewModel)
        ) {

            PickImageFromGallery(defaultViewModel = defaultViewModel)
            
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "Naam: ${defaultViewModel.newName ?: "vul een naam in"}",color = defaultViewModel.getTextColor())
            TextFieldWithHideKeyboardOnImeAction("Verander de naam") {
                defaultViewModel.newName = it
            }

            Spacer(modifier = Modifier.height(50.dp))
            Text(text ="Beschrijving: ${defaultViewModel.newDescription ?: "vul een beschrijving in"}",color = defaultViewModel.getTextColor())
            TextFieldWithHideKeyboardOnImeAction("Verander de beschrijving") {
                defaultViewModel.newDescription = it
            }

            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                    if(defaultViewModel.newName == null) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Je moet een naam meegeven.")
                        }
                    }else if(defaultViewModel.newDescription == null) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Je moet een beschrijving meegeven.")
                        }
                    }else{
                        defaultViewModel.updateItem()
                        defaultViewModel.newID = null
                        defaultViewModel.newName = null
                        defaultViewModel.newImageUri = null
                        defaultViewModel.newDescription = null
                        defaultViewModel.newBitmap = null
                        navController.navigate(Screen.HomeScreen.route)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Opslaan",color = defaultViewModel.getTextColor())
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}