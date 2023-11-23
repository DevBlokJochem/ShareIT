package com.plcoding.m3_bottomnavigation.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plcoding.m3_bottomnavigation.BackgroundColor
import com.plcoding.m3_bottomnavigation.LoadBottomNavigationTheme
import com.plcoding.m3_bottomnavigation.Screen
import com.plcoding.m3_bottomnavigation.ui.DefaultViewModel
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

    Scaffold(
        bottomBar = { LoadBottomNavigationTheme(navController, Screen.UploadScreen) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .BackgroundColor(defaultViewModel)
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(50.dp))
            Text("Naam: ${defaultViewModel.newName}")
            TextFieldWithHideKeyboardOnImeAction("Verander de naam") {
                defaultViewModel.newName = it
            }

            Spacer(modifier = Modifier.height(50.dp))
            Text("Beschrijving: ${defaultViewModel.newDescription}")
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
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Opslaan")
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}