package com.example.dropdown

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dropdown(modifier: Modifier = Modifier) {
    val item= listOf("Tab 1","Tab 2")
    var isSelected by remember { 
        mutableStateOf("")
    }
    var expanded by remember { 
        mutableStateOf(false)
    }
    
    Column(modifier=modifier.fillMaxSize().padding(top = 50.dp),
        verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
        ) {
            OutlinedTextField(
                value = isSelected,
                onValueChange = {},
                modifier=modifier.menuAnchor().fillMaxWidth(),
                readOnly = true,
                trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded)}
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded=false
                },
            ) {
                item.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            isSelected=it
                            expanded=false
                        },
                    )
                }
            }
        }
    }
}