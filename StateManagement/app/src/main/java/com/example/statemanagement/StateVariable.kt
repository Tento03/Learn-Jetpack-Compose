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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import com.example.statemanagement.ui.theme.StateManagementTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(){
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var showDialogue by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier
        .padding(8.dp)
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Login", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = email,
            onValueChange = { newText->
                email=newText
            },
            placeholder = { Text("Search here") },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) }
            )
        OutlinedTextField(
            value = password,
            onValueChange = { newText->
                password=newText
            },
            placeholder = { Text("Password") },
            leadingIcon = {Icon(imageVector = Icons.Default.Lock, contentDescription = null)}
        )
        Button(onClick = {showDialogue=true}) {
            Text("Login")
        }

        if (showDialogue){
            AlertDialog(
                onDismissRequest = {
                    showDialogue=false
                },
                title = {
                    Text("User Informastion")
                },
                text = {
                    Text("Email:$email, Password:$password")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialogue=false
                        }
                    ) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDialogue=false
                        }
                    ) {
                        Text("Dismiss")
                    }
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview(){
    StateManagementTheme {
        LoginScreen()
    }
}