package com.example.sandy.notifysample

import android.support.v4.app.NotificationCompat

enum class Priority(priority: Int) {
    DEFAULT(NotificationCompat.PRIORITY_DEFAULT),
    LOW(NotificationCompat.PRIORITY_LOW),
    HIGH(NotificationCompat.PRIORITY_HIGH)
}