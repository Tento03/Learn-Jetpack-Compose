package com.example.stopwatchcompose

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class StopwatchService : Service() {

    private var startTime = 0L
    private var isRunning = false

    companion object {
        val elapsedTimeFlow = MutableStateFlow(0L) // StateFlow untuk waktu
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
        const val NOTIFICATION_ID = 1
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> startStopwatch()
            ACTION_STOP -> stopStopwatch()
        }
        return START_STICKY
    }

    private fun startStopwatch() {
        if (!isRunning) {
            startTime = SystemClock.elapsedRealtime() - elapsedTimeFlow.value
            isRunning = true
            startForeground(NOTIFICATION_ID, createNotification("Stopwatch started"))
            CoroutineScope(Dispatchers.IO).launch {
                while (isRunning) {
                    val currentTime = SystemClock.elapsedRealtime() - startTime
                    elapsedTimeFlow.value = currentTime // Perbarui StateFlow
                    updateNotification(formatTimeWithDays(currentTime))
                    delay(1000L)
                }
            }
        }
    }

    private fun stopStopwatch() {
        isRunning = false
        stopForeground(true)
        stopSelf()
    }

    private fun createNotification(contentText: String): Notification {
        val channelId = "stopwatch_channel"
        val channelName = "Stopwatch Service"
        val notificationManager = getSystemService(NotificationManager::class.java)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)
            notificationManager.createNotificationChannel(channel)
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Stopwatch Running")
            .setContentText(contentText)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Ganti dengan ikon Anda
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun updateNotification(contentText: String) {
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.notify(NOTIFICATION_ID, createNotification(contentText))
    }

    private fun formatTimeWithDays(milliseconds: Long): String {
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
}