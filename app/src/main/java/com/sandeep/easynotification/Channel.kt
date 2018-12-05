package com.sandeep.easynotification

import android.app.NotificationManager

data class Channel(
    val channelId: String,
    val name: String,
    val description: String,
    val importance: ChannelImportance = ChannelImportance.IMPORTANCE_DEFAULT
)

enum class ChannelImportance(i: Int) {
    IMPORTANCE_DEFAULT(NotificationManager.IMPORTANCE_DEFAULT),
    IMPORTANCE_HIGH(NotificationManager.IMPORTANCE_HIGH),
    IMPORTANCE_LOW(NotificationManager.IMPORTANCE_LOW),
    IMPORTANCE_MAX(NotificationManager.IMPORTANCE_MAX),
    IMPORTANCE_MIN(NotificationManager.IMPORTANCE_MIN),
    IMPORTANCE_NONE(NotificationManager.IMPORTANCE_NONE),
    IMPORTANCE_UNSPECIFIED(NotificationManager.IMPORTANCE_UNSPECIFIED)
}