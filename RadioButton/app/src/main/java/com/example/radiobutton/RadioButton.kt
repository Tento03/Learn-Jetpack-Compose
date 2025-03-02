package com.example.radiobutton

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LearnRadioButton() {
    val options= listOf("Kotlin","Java","PHP","Python")

    var isSelected by remember {
        mutableStateOf(options[0])
    }

    Column(modifier = Modifier.padding(start = 40.dp, top = 120.dp)) {
        options.forEach { option->
            Row { 
                RadioButton(
                    selected = option==isSelected ,
                    onClick = {
                        isSelected=option
                    },
                )
                Text(option)
            }
        }
    }

}