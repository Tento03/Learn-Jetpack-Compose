package com.example.bottomnavigationcompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bottomnavigationcompose.model.BottomItem

@Composable
fun BottomBar(navController: NavController){
    var botItem= listOf(
        BottomItem("Home",Icons.Default.Home,"Home"),
        BottomItem("Favorite",Icons.Default.Favorite,"Favorite"),
        BottomItem("Settings",Icons.Default.Settings,"Settings"),
    )

    var setClicked by remember {
        mutableStateOf(0)
    }

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute=navBackStackEntry?.destination?.route

        botItem.forEachIndexed { index, bottomItem ->
            NavigationBarItem(
                selected = currentRoute==bottomItem.route,
                onClick = {
                    navController.navigate(bottomItem.route){
                        launchSingleTop=true
                        restoreState=true
                        popUpTo(navController.graph.startDestinationId){
                            saveState=true
                        }
                    }
                          },
                icon = { Icon(imageVector = bottomItem.icon,contentDescription = null) },
                label = { Text(bottomItem.title) }
            )
        }
    }
}