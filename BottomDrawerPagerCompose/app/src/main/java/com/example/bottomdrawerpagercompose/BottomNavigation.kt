package com.example.bottomdrawerpagercompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bottomdrawerpagercompose.model.Bottom

@Composable
fun BottomNavigation(navController: NavController) {
    val bottomList= listOf(
        Bottom("Home", Icons.Default.Home,"Home"),
        Bottom("Drawer",Icons.Default.Menu,"Drawer")
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        bottomList.forEachIndexed { index, bottom ->
            NavigationBarItem(
                selected = currentRoute==bottom.route,
                onClick = {
                    navController.navigate(bottom.route){
                        restoreState=true
                        launchSingleTop=true

                        popUpTo(navController.graph.startDestinationId){
                            saveState=true
                        }
                    }
                },
                icon ={
                    Icon(bottom.icon,null)
                },
                label = {
                    Text(bottom.label)
                }
            )
        }
    }
}