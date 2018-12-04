package com.example.sandy.notifysample

import android.content.Context
import android.text.TextUtils


fun Context.simpleNotifier() = PushNotification(this)

fun Context.headsUpNotifier(channelID: String = ""): PushNotification {
    val notification = PushNotification(this)

    notification.config = NotificationConfig.Companion.Builder().apply {
        if (!TextUtils.isEmpty(channelID))
            setChannel(channelID)
        setPriority(Priority.HIGH)
        setVibration(true)
    }.build()

    return notification
}

fun Context.channelNotifier(channelID: String): PushNotification {
    val notification = PushNotification(this)

    notification.config = NotificationConfig.Companion.Builder().apply {

        setChannel(channelID)

    }.build()

    return notification
}

fun Context.fixedNotification(channelID: String = "", headsUp: Boolean = false): PushNotification {
    val notification = PushNotification(this)

    notification.config = NotificationConfig.Companion.Builder().apply {

        setCancellable(false)
        if (!TextUtils.isEmpty(channelID)) setChannel(channelID)
        if (headsUp) {
            setPriority(Priority.HIGH)
            setVibration(true)
        }

    }.build()

    return notification
}

