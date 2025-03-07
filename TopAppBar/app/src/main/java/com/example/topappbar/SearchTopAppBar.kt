@file:Suppress("UNREACHABLE_CODE")

package com.example.topappbar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.topappbar.ui.theme.TopAppBarTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchTopAppBar() {
    var isSearching by remember { 
        mutableStateOf(false)
    }
    var searchQuery by remember { 
        mutableStateOf("")
    }
    
    Scaffold(
        topBar = {
            if (isSearching){
                TopAppBar(
                    title = {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = {
                                searchQuery=it
                            }
                        )
                    },
                    actions = {
                        IconButton(onClick = {isSearching=false}) { 
                            Icon(imageVector = Icons.Filled.Close,contentDescription = null)
                        }
                    },
                )
            }
            else{
                TopAppBar(
                    title = { Text("Home") },
                    actions = {
                        IconButton(
                            onClick = {isSearching=true}
                        ) {
                            Icon(imageVector = Icons.Filled.Search,contentDescription = null)
                        }
                    }
                )
            }
        }
    ) { 
        
    }
}

@Composable
@Preview(showBackground = true)
fun SearchTopAppBarPreview(){
    TopAppBarTheme { 
        SearchTopAppBar()
    }
}