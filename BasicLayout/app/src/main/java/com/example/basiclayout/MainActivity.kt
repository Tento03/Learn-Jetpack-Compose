package com.example.basiclayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiclayout.ui.theme.BasicLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
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
    BasicLayoutTheme {
        Greeting("Android")
    }
}

@Composable
fun rowProfile(){
    Row() {
        Text(text = "Christento")
        Spacer(modifier = Modifier.size(5.dp))
        Text(text = "Siregar")
    }
}

@Composable
@Preview(showBackground = true)
fun rowProfilePreview(){
    BasicLayoutTheme {
        rowProfile()
    }
}

@Composable
fun ColumnProfile(){
    Column() {
        Text(text = "Sakamoto")
        Text(text = "Taro")
    }
}

@Composable
@Preview(showBackground = true)
fun columnProfilePreview(){
    BasicLayoutTheme {
        ColumnProfile()
    }
}

@Composable
fun boxProfile(){
    Box() {
        Text(text = "Luffy")
        Text(text = "Zoro")
    }
}

@Composable
@Preview(showBackground = true)
fun boxProfilePreview(){
    BasicLayoutTheme {
        boxProfile()
    }
}

@Composable
fun userProfile(){
    Row(modifier = Modifier) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        Column() {
            Text(text = "Tento")
            Text(text = "Siregar")
        }
        Icon(imageVector = Icons.Default.Check,
            contentDescription = null,
            modifier = Modifier
                .size(10.dp)
                .align(Alignment.Top)
            )
    }
}

@Composable
@Preview(showBackground = true)
fun userProfileReview(){
    BasicLayoutTheme {
        userProfile()
    }
}

