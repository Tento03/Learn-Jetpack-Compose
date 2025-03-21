package com.example.checkbox

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LearnCheckBox() {

    // Daftar item
    val todoList = listOf("Kotlin", "Java", "PHP", "Python")

    // State untuk menyimpan status checked dari setiap item
    val isChecked = remember {
        todoList.map { mutableStateOf(false) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.img),
                contentScale = ContentScale.FillWidth
            )
    ) {
        Column(modifier = Modifier.padding(start = 40.dp, top = 120.dp)) {
            // Menampilkan daftar checkbox
            todoList.forEachIndexed { index, s ->
                Row {
                    Checkbox(
                        checked = isChecked[index].value,
                        onCheckedChange = {
                            isChecked[index].value = it
                        },
                    )
                    Text(s)
                }
            }

            // Mengambil dan menampilkan item yang dicentang
            val checkedItems = todoList.filterIndexed { index, s ->
                isChecked[index].value
            }.joinToString(", ")

            // Menampilkan teks item yang dicentang
            Text(
                text = "Items Checked: $checkedItems",
                modifier = Modifier.padding(top = 20.dp)
            )
        }
    }
}