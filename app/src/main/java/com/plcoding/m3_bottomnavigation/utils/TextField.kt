package com.plcoding.m3_bottomnavigation.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import com.plcoding.m3_bottomnavigation.ui.DefaultViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithHideKeyboardOnImeAction(label: String, defaultViewModel: DefaultViewModel, callback: (action: String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                callback.invoke(text)
            }
        )
    )
}

@Composable
fun TwoOptionsButton(onOptionSelected: (String) -> Unit) {
    var selectedOption by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        RadioGroup(
            options = listOf("Verhuren", "Aanvragen"),
            selectedOption = selectedOption,
            onOptionSelected = {
                selectedOption = it
                onOptionSelected.invoke(it)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun RadioGroup(
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit
) {
    options.forEach { option ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onOptionSelected(option) }
                .padding(8.dp)
        ) {
            RadioButton(
                selected = option == selectedOption,
                onClick = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = option)
        }
    }
}