package com.sandeep.easynotification.notification.models

import android.app.PendingIntent
import androidx.annotation.DrawableRes

data class NotificationAction(
    val name: String,
    @DrawableRes val icon: Int = 0,
    val pendingIntent: PendingIntent?
)