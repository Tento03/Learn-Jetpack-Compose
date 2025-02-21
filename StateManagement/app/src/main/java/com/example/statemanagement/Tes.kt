package com.example.statemanagement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class TesState(initText:String,initBoolean: Boolean){
    var email by mutableStateOf(initText)
    var password by mutableStateOf(initText)
    var showDialog by mutableStateOf(initBoolean)
}

@Composable
fun TesStateRemember(initText: String,initBoolean: Boolean):TesState= remember{
    TesState(initText,initBoolean)
}

@Composable
fun LoginStateRemember(state: TesState= TesStateRemember(initText = "", initBoolean = false)) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Login", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = state.email,
            onValueChange = { newText ->
                state.email = newText
            },
            placeholder = { Text("Search here") },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) }
        )
        OutlinedTextField(
            value = state.password,
            onValueChange = { newText ->
                state.password = newText
            },
            placeholder = { Text("Password") },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) }
        )
        Button(onClick = { state.showDialog = true }) {
            Text("Login")
        }
        if (state.showDialog){
            AlertDialog(
                onDismissRequest = {},
                title = { Text("User Notif") },
                text = { Text("Email:{${state.email}},password:${state.password}") },
                confirmButton = {
                    TextButton(onClick = { state.showDialog =false } ) {
                        Text("Ok")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {state.showDialog=false} ) {
                        Text("Ok")
                    }
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TesStatePreview(){
    MaterialTheme {
      LoginStateRemember()
    }
}