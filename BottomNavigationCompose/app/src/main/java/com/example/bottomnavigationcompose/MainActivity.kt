package com.example.bottomnavigationcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationcompose.ui.theme.BottomNavigationComposeTheme

class MainActivity : ComponentActivity() {
    companion object{
        const val KEY="1"
    }
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomNavigationComposeTheme {
                val navController= rememberNavController()
                Scaffold(bottomBar = { BottomBar(navController=navController) }) { innerPadding->
                    NavHost(navController = navController, startDestination = "Home",
                        modifier = Modifier.padding(innerPadding)){
                        composable(route = "Home"){
                            HomeView(navController)
                        }
                        composable(route = "Settings"){
                            SettingsView(navController)
                        }
                        composable(route = "Favorite"){
                           FavoriteView(navController)
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
    BottomNavigationComposeTheme {
        Greeting("Android")
    }
}