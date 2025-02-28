package com.example.navigationdrawercompose

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigationdrawercompose.ui.theme.GreenJc
import com.example.tes.Favorite
import com.example.tes.Home
import com.example.tes.Setting
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer(){
    val navController= rememberNavController()
    val coroutineScope= rememberCoroutineScope()
    val drawerState= rememberDrawerState(initialValue = DrawerValue.Closed)
    val context= LocalContext.current.applicationContext

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute=navBackStackEntry?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Box(modifier = Modifier
                    .background(GreenJc)
                    .fillMaxWidth()
                    .height(150.dp))
                {
                    Text(text = "")
                }
                Divider()
                NavigationDrawerItem(
                    label = { Text("Home", color = GreenJc) },
                    selected = currentRoute=="Home",
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navController.navigate(Screens.Home.screen){
                            popUpTo(0)
                        }
                    },
                    icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
                )
                NavigationDrawerItem(
                    label = { Text("Favorite", color = GreenJc) },
                    selected = currentRoute=="Favorite",
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navController.navigate(Screens.Favorite.screen){
                            popUpTo(0)
                        }
                    },
                    icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = null) },
                )
                NavigationDrawerItem(
                    label = { Text("Settings", color = GreenJc) },
                    selected = currentRoute=="Settings",
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navController.navigate(Screens.Setting.screen){
                            popUpTo(0)
                        }
                    },
                    icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = null) },
                )
            }
        },
    ) {
        Scaffold (
            topBar = {
                val coroutineScope = rememberCoroutineScope()
                TopAppBar(
                    title = { Text("Wa") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = GreenJc,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                Icons.Rounded.Menu, contentDescription = null
                            )
                        }
                    }
                )
            }
        ) {
            NavHost(navController=navController, startDestination = "Home"){
                composable(Screens.Home.screen){
                    Home()
                }
                composable(Screens.Favorite.screen){
                    Favorite()
                }
                composable(Screens.Setting.screen){
                    Setting()
                }
            }
        }
    }
}