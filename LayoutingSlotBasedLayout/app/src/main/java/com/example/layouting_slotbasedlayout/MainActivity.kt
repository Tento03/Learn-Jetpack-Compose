package com.example.layouting_slotbasedlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layouting_slotbasedlayout.ui.theme.LayoutingSlotBasedLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutingSlotBasedLayoutTheme {
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
    LayoutingSlotBasedLayoutTheme {
        Greeting("Android")
    }
}

@Composable
fun buttonLike(){
    Button(
        onClick = {},
        contentPadding = PaddingValues(start = 20.dp, top = 12.dp, end = 20.dp, bottom = 12.dp)
    ) {
        Icon(imageVector = Icons.Filled.Favorite,
            contentDescription = "Like",
            modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = "Like")
    }
}

@Composable
@Preview(showBackground = true)
fun buttonLikePreview(){
    LayoutingSlotBasedLayoutTheme {
        buttonLike()
    }
}