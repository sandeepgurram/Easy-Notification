package com.example.sandy.notifysample

import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.widget.RemoteViews
import com.sandeep.easynotification.*
import com.sandeep.easynotification.EasyNotification.Companion.channels
import com.sandeep.easynotification.EasyNotification.Companion.defaultChannel


fun Context.simpleNotifier(): PushNotification {

    val notification = PushNotification(this)

    notification.config = NotificationConfig.Companion.Builder().apply {

        setChannel(getChannelID())
    }.build()

    return notification
}

fun Context.headsUpNotifier(channelID: String = ""): PushNotification {
    val notification = PushNotification(this)

    notification.config = NotificationConfig.Companion.Builder().apply {
        setChannel(getChannelID(channelID))
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

        setChannel(getChannelID(channelID))

    }.build()

    return notification
}

/**
 * From Android 7.0 (API level 24) and higher, the system automatically builds a summary for your group using snippets of text from each notification.
 * <br>
 * To support older versions, which cannot show a nested group of notifications, you must create an extra notification that acts as the summary
 */
fun Context.compactNotifier(
    channelID: String = "", groupName: String? = null,
    isGroupSummary: Boolean = false
): PushNotification {
    val notification = PushNotification(this)

    notification.config = simpleNotifier().config.builder.apply {

        setChannel(getChannelID(channelID))

        if (!TextUtils.isEmpty(groupName)) {
            setGroup(groupName!!)
            isGroupSummary(isGroupSummary)
        }

    }.build()

    return notification
}

private fun getChannelID(channelID: String? = null): String {

    if (channels?.size ?: 0 == 0)
        throw Throwable("Notification channels are not initialised")

    var channelId = ""

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        if (TextUtils.isEmpty(channelID)) {
            channelId = defaultChannel!!.channelId
        } else {
            channelId = channelID!!
        }

    }
    return channelId
}

fun Context.fixedNotification(channelID: String = "", headsUp: Boolean = false): PushNotification {
    val notification = PushNotification(this)

    notification.config = NotificationConfig.Companion.Builder().apply {

        setCancellable(false)

        setChannel(getChannelID(channelID))

        if (headsUp) {
            setPriority(Priority.HIGH)
            setVibration(true)
        }

    }.build()

    return notification
}

fun Context.customViewNotification(
    channelID: String,
    notificationLayoutExpanded: RemoteViews?,
    notificationLayout: RemoteViews?,
    fullCustomised: Boolean = false
): EasyNotification {

    val customPushNotification =
        CustomPushNotification(
            this,
            notificationLayout = notificationLayout,
            notificationLayoutExpanded = notificationLayoutExpanded,
            fullCustomised = fullCustomised
        )

    customPushNotification.config = NotificationConfig.Companion.Builder().apply {
        setChannel(getChannelID(channelID))
        setPriority(Priority.HIGH)
        setVibration(true)
    }.build()

    return customPushNotification

}


