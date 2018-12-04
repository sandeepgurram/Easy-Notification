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

fun Context.channelNotifier(
    channelID: String
): PushNotification {
    val notification = PushNotification(this)

    notification.config = NotificationConfig.Companion.Builder().apply {

        setChannel(channelID)

    }.build()

    return notification
}


/**
 * From Android 7.0 (API level 24) and higher, the system automatically builds a summary for your group using snippets of text from each notification.
 * <br>
 * To support older versions, which cannot show a nested group of notifications, you must create an extra notification that acts as the summary
 */
fun Context.compactChannelNotifier(
    channelID: String,
    groupName: String? = null,
    isGroupSummary: Boolean = false
): PushNotification {
    val notification = PushNotification(this)

    notification.config = channelNotifier(channelID).config.builder.apply {

        setChannel(channelID)

        if (!TextUtils.isEmpty(groupName)) {
            setGroup(groupName!!)
            isGroupSummary(isGroupSummary)
        }

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

