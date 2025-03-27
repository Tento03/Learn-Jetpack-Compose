package com.example.timercompose

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun TimerScreen(context: Context) {
    var isRunning by remember { mutableStateOf(false) }
    var hoursInput by remember { mutableStateOf("") } // Input jam
    var minutesInput by remember { mutableStateOf("") } // Input menit
    var secondsInput by remember { mutableStateOf("") } // Input detik

    // Mendengarkan pembaruan waktu dari service
    val remainingTime by TimerService.remainingTimeFlow.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Tampilkan waktu tersisa
        Text(
            text = formatTime(remainingTime),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input jam, menit, dan detik
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = hoursInput,
                onValueChange = { newValue -> hoursInput = newValue },
                label = { Text("Hours") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = minutesInput,
                onValueChange = { newValue -> minutesInput = newValue },
                label = { Text("Minutes") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = secondsInput,
                onValueChange = { newValue -> secondsInput = newValue },
                label = { Text("Seconds") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol kontrol timer
        Button(onClick = {
            if (isRunning) {
                context.stopTimerService(context)
            } else {
                // Hitung total waktu dalam detik dari input jam, menit, dan detik
                val totalSeconds = (hoursInput.toLongOrNull() ?: 0) * 3600 +
                        (minutesInput.toLongOrNull() ?: 0) * 60 +
                        (secondsInput.toLongOrNull() ?: 0)
                if (totalSeconds > 0) {
                    context.startTimerService(context, totalSeconds)
                }
            }
            isRunning = !isRunning
        }) {
            Text(text = if (isRunning) "Stop Timer" else "Start Timer")
        }
    }
}

fun Context.startTimerService(context: Context, timeInSeconds: Long) {
    val intent = Intent(context, TimerService::class.java)
    intent.action = TimerService.ACTION_START
    intent.putExtra("time_in_seconds", timeInSeconds)
    ContextCompat.startForegroundService(context, intent)
}

fun Context.stopTimerService(context: Context) {
    val intent = Intent(context, TimerService::class.java)
    intent.action = TimerService.ACTION_STOP
    ContextCompat.startForegroundService(context, intent)
}

fun formatTime(milliseconds: Long): String {
    val seconds = (milliseconds / 1000) % 60
    val minutes = (milliseconds / (1000 * 60)) % 60
    val hours = (milliseconds / (1000 * 60 * 60)) % 24
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}