package com.sandeep.easynotification.notification.models

import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi

data class Channel(
    val channelId: String,
    val name: String,
    val description: String,
    val importance: ChannelImportance = ChannelImportance.IMPORTANCE_DEFAULT,
    val isDefaulf: Boolean = false
)

@RequiresApi(Build.VERSION_CODES.N)
enum class ChannelImportance(i: Int) {
    IMPORTANCE_DEFAULT(NotificationManager.IMPORTANCE_DEFAULT),
    IMPORTANCE_HIGH(NotificationManager.IMPORTANCE_HIGH),
    IMPORTANCE_LOW(NotificationManager.IMPORTANCE_LOW),
    IMPORTANCE_MAX(NotificationManager.IMPORTANCE_MAX),
    IMPORTANCE_MIN(NotificationManager.IMPORTANCE_MIN)
}