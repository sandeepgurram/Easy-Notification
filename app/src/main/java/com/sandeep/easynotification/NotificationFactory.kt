package com.example.sandy.notifysample

import android.content.Context


fun Context.simpleNotifier() = PushNotification(this)

fun Context.channelNotifier(channelID: String): PushNotification {
    val notification = PushNotification(this)

    val buildConfig = notification.config.builder
    buildConfig.setChannel(channelID)

    notification.config = buildConfig.build()

    return notification
}

