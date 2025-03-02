@file:Suppress("UNREACHABLE_CODE")

package com.example.floatingactionbutton

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.floatingactionbutton.ui.theme.FloatingActionButtonTheme
import com.example.floatingactionbutton.ui.theme.GreenJC

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LearnFloatingActionButton() {
    val context= LocalContext.current.applicationContext
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { Toast.makeText(context,"Hi",Toast.LENGTH_SHORT).show() },
                containerColor = GreenJC,
                contentColor = Color.White,
            ) {
                Icon(imageVector = Icons.Filled.Add,contentDescription = null)
            }
        }
    ) {

    }
}

@Preview(showBackground = true)
@Composable
private fun FABPrev() {
    FloatingActionButtonTheme {
        LearnFloatingActionButton()
    }
}