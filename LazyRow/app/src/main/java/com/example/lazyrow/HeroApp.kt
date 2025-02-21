package com.example.lazyrow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.lazyrow.`object`.HeroObject
import com.example.lazyrow.ui.theme.LazyRowTheme

@Composable
fun HeroApp(){
    Box(modifier=Modifier){
        LazyColumn {
            items(HeroObject.heroes){
                HeroItem(name = it.name, photoUrl = it.photoUrl)
            }
        }
    }
}

@Composable
fun HeroItem(name:String,photoUrl:String){
    Row(verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .clip(CircleShape)
                .size(60.dp)
        )
        Text(text = name, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HeroItemPreview(){
    LazyRowTheme {
        HeroItem("Pangeran Diponegoro","")
    }
}

@Composable
@Preview(showBackground = true)
fun HeroAppPreview(){
    LazyRowTheme {
        HeroApp()
    }
}