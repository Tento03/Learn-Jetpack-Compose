package com.example.stopwatchcompose

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun StopwatchScreen(context: Context) {
    var isRunning by remember { mutableStateOf(false) }

    // Menggunakan StateFlow untuk mendengarkan pembaruan waktu
    val elapsedTime by StopwatchService.elapsedTimeFlow.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Tampilkan waktu dalam format hari, jam, menit, dan detik
        Text(
            text = "Elapsed Time: ${formatTimeWithDays(elapsedTime)}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (isRunning) {
                context.stopStopwatchService(context)
            } else {
                context.startStopwatchService(context)
            }
            isRunning = !isRunning
        }) {
            Text(text = if (isRunning) "Stop Stopwatch" else "Start Stopwatch")
        }
    }
}

fun Context.startStopwatchService(context: Context) {
    val intent = Intent(context, StopwatchService::class.java)
    intent.action = StopwatchService.ACTION_START
    ContextCompat.startForegroundService(context, intent)
}

fun Context.stopStopwatchService(context: Context) {
    val intent = Intent(context, StopwatchService::class.java)
    intent.action = StopwatchService.ACTION_STOP
    ContextCompat.startForegroundService(context, intent)
}

fun formatTimeWithDays(milliseconds: Long): String {
    val totalSeconds = milliseconds / 1000
    val days = totalSeconds / (24 * 60 * 60)
    val hours = (totalSeconds % (24 * 60 * 60)) / (60 * 60)
    val minutes = (totalSeconds % (60 * 60)) / 60
    val seconds = totalSeconds % 60

    return if (days > 0) {
        "${days}d ${hours}h ${minutes}m ${seconds}s"
    } else {
        "${hours}h ${minutes}m ${seconds}s"
    }
}