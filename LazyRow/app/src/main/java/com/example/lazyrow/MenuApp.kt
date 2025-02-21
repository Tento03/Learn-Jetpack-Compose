package com.example.lazyrow

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.lazyrow.model.Menu
import com.example.lazyrow.`object`.dummyListTopMenus
import com.example.lazyrow.ui.theme.LazyRowTheme

@Composable
fun MenuApp(){
    Box {
        LazyColumn {
            items(dummyListTopMenus){
                MenuItem(it)
            }
        }
    }
}

@Composable
fun MenuItem(menu: Menu){
    Row (verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(model = menu.img,
            contentDescription = null,
            modifier = Modifier
                .padding(80.dp)
                .size(60.dp)
                .clip(CircleShape))
        Text(menu.title, modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth())
    }
}

@Composable
@Preview(showBackground = true)
fun MenuAppPreview(){
    LazyRowTheme {
        MenuApp()
    }
}