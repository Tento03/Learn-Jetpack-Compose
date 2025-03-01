package com.example.bottomdrawerpagercompose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bottomdrawerpagercompose.model.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationDrawer(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val context= LocalContext.current.applicationContext

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Box(modifier = Modifier.fillMaxWidth()
                    .height(150.dp)
                    .background(Color.Green)
                )
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text("Home") },
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                        navController.navigate(Screen.Home.screen){
                            popUpTo(navController.graph.startDestinationId){
                                saveState
                            }
                        }
                    },
                    icon = {
                        Icon(imageVector = Icons.Default.Home,contentDescription = null)
                    },
                )
                NavigationDrawerItem(
                    label = { Text("Favorite") },
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                        navController.navigate(Screen.Favorite.screen){
                            popUpTo(navController.graph.startDestinationId){
                                saveState
                            }
                        }
                    },
                    icon = {
                        Icon(imageVector = Icons.Default.Favorite,contentDescription = null)
                    },
                )
                NavigationDrawerItem(
                    label = { Text("Setting") },
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                        navController.navigate(Screen.Setting.screen){
                            popUpTo(navController.graph.startDestinationId){
                                saveState
                            }
                        }
                    },
                    icon = {
                        Icon(imageVector = Icons.Default.Settings,contentDescription = null)
                    },
                )
                NavigationDrawerItem(
                    label = { Text("Pager") },
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                        navController.navigate(Screen.Pager.screen){
                            popUpTo(navController.graph.startDestinationId){
                                saveState
                            }
                        }
                    },
                    icon = {
                        Icon(imageVector = Icons.Default.Info,contentDescription = null)
                    },
                )
            }
        },
        )
    {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Menu") },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(imageVector = Icons.Rounded.Menu,"menu")
                        }
                    },
                )
            }
        ) {
        }
    }
}