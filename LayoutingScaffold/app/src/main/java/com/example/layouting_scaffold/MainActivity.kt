package com.example.layouting_scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.layouting_scaffold.ui.theme.LayoutingScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutingScaffoldTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LayoutingScaffoldTheme {
        Greeting("Android")
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun ScaffoldSample(){
    Scaffold(
        topBar = {
            TopAppBar(title = Text(text = "My Scaffold")) {
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    ) {
        Box(modifier = Modifier
            .padding(it)
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(text = "Hello Compose")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ScaffoldSamplePreview(){
    LayoutingScaffoldTheme {
        ScaffoldSample()
    }
}
fun TopAppBar(title: Unit, function: () -> Unit) {

}
