package com.example.sandy.notifysample

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.text.TextUtils
import com.example.sandy.notifysample.EasyNotification.Companion.notificationId

class PushNotification(private val context: Context) : EasyNotification {

    override var config: NotificationConfig = NotificationConfig.Companion.Builder().build()


    private fun showNotification(id: Int, notification: Notification) {
        with(NotificationManagerCompat.from(context)) {
            notify(id, notification)
        }
    }

    private fun getBuilderWith(title: String, content: String, detials: String, pendingIntent: PendingIntent?) =
        getBuilder().apply {
            setContentTitle(title)
            setContentText(content)

            if (!TextUtils.isEmpty(content))
                setStyle(NotificationCompat.BigTextStyle().bigText(detials))

            pendingIntent?.let {
                setContentIntent(pendingIntent)
            }
        }

    private fun getBuilder() = NotificationCompat.Builder(context, config.channel).apply {

        setSmallIcon(config.icon)
        priority = config.priority.ordinal
        setAutoCancel(config.cancellable)
        if (config.vibrate && Build.VERSION.SDK_INT >= 21)
            setVibrate(config.vibrationPattern)

        setCategory(NotificationCompat.CATEGORY_MESSAGE)

        setGroup(config.group)
        setGroupSummary(config.isGroupSummary)

    }

    override fun notify(title: String, content: String, detials: String, pendingIntent: PendingIntent?): Int {

        notificationId++

        var mBuilder = getBuilderWith(title, content, detials, pendingIntent)

        showNotification(notificationId, mBuilder.build())

        return notificationId
    }

    override fun update(
        notificationId: Int,
        title: String,
        content: String,
        detials: String,
        pendingIntent: PendingIntent?
    ) {
        var mBuilder = getBuilderWith(title, content, detials, pendingIntent)

        showNotification(EasyNotification.notificationId, mBuilder.build())
    }

    /**
     * Pass the id returned by @notify
     */
    override fun remove(notificationId: Int) {
        with(NotificationManagerCompat.from(context)) {
            cancel(notificationId)
        }
    }

    override fun removeAll() {
        with(NotificationManagerCompat.from(context)) {
            cancelAll()
        }
    }
}