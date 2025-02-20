package com.example.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.bottomnavigation.ui.theme.BottomNavigationTheme

data class BottomItem(
    val title:String,
    val icon:ImageVector
)
@Composable
fun BottomNavView(){
    val item= listOf(
        BottomItem(title = "Home", icon = Icons.Default.Home),
        BottomItem(title = "Person", icon = Icons.Default.Person)
    )

    var selectedItem by remember {
        mutableStateOf(0)
    }

    NavigationBar {
        item.forEachIndexed{ index, bottomItem ->
            NavigationBarItem(
                selected = selectedItem==index,
                onClick = {selectedItem=index},
                icon = {Icon(imageVector = bottomItem.icon,contentDescription = bottomItem.title)},
                label = { Text(bottomItem.title) },
            )
        }
    }
}

@Composable
@Preview
fun BottomItemPreview(){
    BottomNavigationTheme {
        BottomNavView()
    }
}