package com.example.sandy.notifysample

import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.text.TextUtils
import com.example.sandy.notifysample.EasyNotification.Companion.notificationId

class PushNotification(private val context: Context) : EasyNotification {


    override var config: NotificationConfig = NotificationConfig.Companion.Builder().build()

    override fun notify(title: String, content: String, detials: String, pendingIntent: PendingIntent?): Int {

        notificationId++

        var mBuilder = NotificationCompat.Builder(context, config.channel).apply {

            setSmallIcon(config.icon)
            setContentTitle(title)
            setContentText(content)
            priority = config.priority.ordinal
            setAutoCancel(config.cancellable)
            if (config.vibrate && Build.VERSION.SDK_INT >= 21)
                setVibrate(config.vibrationPattern)

            setCategory(NotificationCompat.CATEGORY_MESSAGE)

            if (!TextUtils.isEmpty(content))
                setStyle(NotificationCompat.BigTextStyle().bigText(detials))

            pendingIntent?.let {
                setContentIntent(pendingIntent)
            }

            setGroup(config.group)
            setGroupSummary(config.isGroupSummary)

        }

        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, mBuilder.build())
        }

        return notificationId
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