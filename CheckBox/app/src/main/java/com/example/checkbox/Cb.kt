package com.example.checkbox

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@Composable
fun CheckBoxListExample() {
    // Data untuk daftar item
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4")

    // State untuk menyimpan status checked dari setiap item
    var checkedStates by remember {
        mutableStateOf(List(items.size) {
            false }
        )}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Menampilkan setiap item sebagai checkbox
        items.forEachIndexed { index, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = checkedStates[index],
                    onCheckedChange = { isChecked ->
                        // Perbarui state untuk item ini
                        val newCheckedStates = checkedStates.toMutableList()
                        newCheckedStates[index] = isChecked
                        checkedStates = newCheckedStates
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = item)
            }
        }

        // Tampilkan item yang dicentang
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Items Checked: ${items.filterIndexed { index, _ -> checkedStates[index] }.joinToString(", ")}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}