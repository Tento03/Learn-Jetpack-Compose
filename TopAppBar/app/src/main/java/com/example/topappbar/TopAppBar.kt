package com.example.topappbar

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.topappbar.ui.theme.GreenJC
import com.example.topappbar.ui.theme.TopAppBarTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnTopAppBar(){
    val context= LocalContext.current.applicationContext
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("WhatsApp") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            Toast.makeText(context,"Hi",Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(painter = painterResource(R.drawable.img),contentDescription = null)
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            Toast.makeText(context,"Hi",Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(imageVector = Icons.Filled.Person,contentDescription = null)
                    }
                    IconButton(
                        onClick = {
                            Toast.makeText(context,"Hi",Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(imageVector = Icons.Filled.Search,contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = GreenJC,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White
                ),
            )
        }
    ) {

    }
}

@Preview
@Composable
private fun LearnTopAppBarPrev() {
    TopAppBarTheme {
        LearnTopAppBar()
    }
}