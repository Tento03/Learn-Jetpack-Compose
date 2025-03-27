package com.example.timercompose

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class TimerService : Service() {

    private var isRunning = false
    private var remainingTime = 0L // Waktu tersisa dalam milidetik
    private lateinit var timerJob: Job

    companion object {
        val remainingTimeFlow = MutableStateFlow(0L) // StateFlow untuk waktu tersisa
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
        const val NOTIFICATION_ID = 1
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> {
                val timeInSeconds = intent.getLongExtra("time_in_seconds", 0)
                startTimer(timeInSeconds * 1000) // Konversi detik ke milidetik
            }
            ACTION_STOP -> stopTimer()
        }
        return START_STICKY
    }

    private fun startTimer(timeInMillis: Long) {
        if (!isRunning) {
            remainingTime = timeInMillis
            isRunning = true
            startForeground(NOTIFICATION_ID, createNotification("Timer started"))
            timerJob = CoroutineScope(Dispatchers.IO).launch {
                while (remainingTime > 0 && isRunning) {
                    delay(1000L)
                    remainingTime -= 1000
                    remainingTimeFlow.value = remainingTime // Perbarui StateFlow
                    updateNotification(formatTime(remainingTime))
                }
                if (remainingTime <= 0) {
                    stopTimer()
                }
            }
        }
    }

    private fun stopTimer() {
        isRunning = false
        timerJob.cancel()
        stopForeground(true)
        stopSelf()
    }

    private fun createNotification(contentText: String): Notification {
        val channelId = "timer_channel"
        val channelName = "Timer Service"
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)
            notificationManager.createNotificationChannel(channel)
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Timer Running")
            .setContentText(contentText)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Ganti dengan ikon Anda
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun updateNotification(contentText: String) {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, createNotification(contentText))
    }

    private fun formatTime(milliseconds: Long): String {
        val seconds = (milliseconds / 1000) % 60
        val minutes = (milliseconds / (1000 * 60)) % 60
        val hours = (milliseconds / (1000 * 60 * 60)) % 24
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}