package com.example.composenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composenavigation.ui.theme.ComposeNavigationTheme

class MainActivity : ComponentActivity() {
    companion object{
        const val KEY="1"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeNavigationTheme {
//                navController
                val navController= rememberNavController()
//                navHost
                NavHost(navController=navController, startDestination = "Login"){
//                 navGraph
                    composable(route = "Home"){
                        HomeView(navController)
                    }
                    composable(route = "Detail/{$KEY}"){ backStackEntry->
                        var userId=backStackEntry.arguments?.getString(KEY)
                        DetailView(navController,userId)
                    }
                    composable(route = "Login"){
                        LoginView(navController)
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
    ComposeNavigationTheme {
        Greeting("Android")
    }
}