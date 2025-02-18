package com.example.layouting_modifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layouting_modifier.ui.theme.LayoutingModifierTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutingModifierTheme {
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
    LayoutingModifierTheme {
        Greeting("Android")
    }
}

@Composable
fun userProfile(name: String, bio:String){
    Row(modifier = Modifier
        .padding(4.dp)
        .clickable(enabled = true, onClick = {})
    ) {
        Image(painter = painterResource(id = R.drawable.sakamoto),
            contentDescription = "sakamoto",
            modifier = Modifier
                .padding(4.dp)
                .size(60.dp)
                .border(2.dp, Color.Blue, CircleShape)
                .clip(CircleShape)
            )
        Spacer(modifier = Modifier.size(4.dp))
        Column(modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            ) {
            Text(text = name, fontWeight = FontWeight.Bold)
            Text(text = bio, modifier = Modifier.offset(x=16.dp))
            Icon(imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                modifier = Modifier.align(Alignment.End)
                )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun userProfilePreview(){
    LayoutingModifierTheme() {
        userProfile(name = "Sakamoto", bio = "Android Developer")
    }
}