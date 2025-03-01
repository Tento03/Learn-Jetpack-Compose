package com.example.bottomdrawerpagercompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bottomdrawerpagercompose.model.Screen
import com.example.bottomdrawerpagercompose.ui.theme.BottomDrawerPagerComposeTheme
import com.example.bottomdrawerpagercompose.uiux.FavoriteScreen
import com.example.bottomdrawerpagercompose.uiux.HomeScreen
import com.example.bottomdrawerpagercompose.uiux.SettingScreen

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomDrawerPagerComposeTheme {
                val navController = rememberNavController()
               Scaffold(bottomBar = { BottomNavigation(navController) }) {
                   NavHost(navController=navController, startDestination = Screen.Home.screen){
                       composable(route = Screen.Home.screen){
                           HomeScreen(navController)
                       }
                       composable(route = Screen.Drawer.screen){
                           NavigationDrawer(navController)
                       }
                       composable(route = Screen.Favorite.screen){
                           FavoriteScreen(navController)
                       }
                       composable(route = Screen.Setting.screen){
                           SettingScreen(navController)
                       }
                       composable(route = Screen.Pager.screen){
                           TabbedViewPager()
                       }
                   }
               }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomDrawerPagerComposeTheme {
        Greeting("Android")
    }
}