package com.example.floatingactionbutton

import android.annotation.SuppressLint
import android.content.res.Resources.Theme
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.floatingactionbutton.ui.theme.FloatingActionButtonTheme
import com.example.floatingactionbutton.ui.theme.GreenJC

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SubFab() {
    val context= LocalContext.current.applicationContext
    var showSubFab by remember {
        mutableStateOf(false)
    }
    var onClosed by remember {
        mutableStateOf(false)
    }

    Scaffold(
        floatingActionButton = {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp),
                
            ) {
                if (showSubFab){
                    FloatingActionButton(
                        onClick = {},
                        containerColor = Color.Blue,
                        contentColor = Color.White,
                        modifier = Modifier.size(56.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Search,contentDescription = null)
                    }
                }
                if (showSubFab){
                    FloatingActionButton(
                        onClick = {},
                        containerColor = Color.Red,
                        contentColor = Color.White,
                        modifier = Modifier.size(56.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Settings,contentDescription = null)
                    }
                }

                FloatingActionButton(
                    onClick = {
                        Toast.makeText(context,"Hi",Toast.LENGTH_SHORT).show()
                        showSubFab=!showSubFab },
                    containerColor = GreenJC,
                    contentColor = Color.White,
                ) {
                    Icon(imageVector = Icons.Default.Add,contentDescription = null)
                }
            }
        }
    ) {

    }
}

@Composable
@Preview(showBackground = true)
fun SubFabPreview(){
    FloatingActionButtonTheme {
        SubFab()
    }
}