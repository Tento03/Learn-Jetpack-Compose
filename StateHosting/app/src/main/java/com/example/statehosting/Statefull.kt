package com.example.statehosting

import android.content.res.Resources.Theme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.statehosting.ui.theme.StateHostingTheme

@Composable
fun Statefull(){
    var count by remember {
        mutableStateOf(0)
    }

    Column {
        Text("Count:$count")
        Button(onClick = {count++}) {
            Text("Tambah")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun StatefullPreview(){
    StateHostingTheme {
        Statefull()
    }
}