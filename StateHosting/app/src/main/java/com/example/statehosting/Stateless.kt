package com.example.statehosting

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
fun MainView(){
    var count by remember {
        mutableStateOf(0)
    }
    Stateless(count,{count++})
}

@Composable
fun Stateless(count:Int,onChange:()->Unit){
    Column {
        Text("Count:$count")
        Button(onClick = onChange) {
            Text("Tambah")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun StatelessPreview(){
    StateHostingTheme {
        MainView()
    }
}