package com.example.composenavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composenavigation.ui.theme.ComposeNavigationTheme

class LoginState(initText:String,initBoolean: Boolean) {
    var email by mutableStateOf(initText)
    var password by mutableStateOf(initText)
    var showDialog by mutableStateOf(initBoolean)
}

@Composable
fun LoginStateRemember(initText: String,initBoolean: Boolean):LoginState= remember {
    LoginState(initText,initBoolean)
}

@Composable
fun LoginView(navController: NavController,state: LoginState= LoginStateRemember(initText = "",initBoolean = false)){
    Column(modifier = Modifier
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Login")
        OutlinedTextField(
            value = state.email,
            onValueChange = { newText->
                state.email=newText
            },
            placeholder = { Text("Email") },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )
        )
        OutlinedTextField(
            value = state.password,
            onValueChange = { newText->
                state.password=newText
            },
            placeholder = { Text("Password") },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = {state.showDialog=true}) {
            Text("Login")
        }
        if (state.showDialog){
            AlertDialog(
                onDismissRequest = {state.showDialog=false},
                title = { Text("User Information") },
                text = { Text("Email:${state.email}, Password:${state.password}") },
                confirmButton = {
                    TextButton(onClick = {state.showDialog=false
                        navController.navigate("Home")
                    }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {state.showDialog=false
                        navController.navigate("Home")
                    }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginViewPreview(){
    ComposeNavigationTheme {
        val navController= rememberNavController()
        LoginView(navController)
    }
}