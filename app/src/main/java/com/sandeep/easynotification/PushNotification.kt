package com.example.sandy.notifysample

import android.app.PendingIntent
import android.content.Context
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.text.TextUtils
import com.example.sandy.notifysample.EasyNotification.Companion.CHANNEL_ID_1
import com.example.sandy.notifysample.EasyNotification.Companion.notificationId

class PushNotification(private val context: Context) : EasyNotification {


    override var config: NotificationConfig = NotificationConfig.Companion.Builder()
        .setCancellable(true)
        .setIcon(R.drawable.ic_launcher_foreground)
        .build()

    override fun notify(title: String, content: String, detials: String, pendingIntent: PendingIntent?): Int {

        notificationId++

        var mBuilder = NotificationCompat.Builder(context, CHANNEL_ID_1).apply {

            setSmallIcon(config.icon)
            setContentTitle(title)
            setContentText(content)
            priority = config.priority
            setAutoCancel(config.cancellable)

            if (!TextUtils.isEmpty(content))
                setStyle(NotificationCompat.BigTextStyle().bigText(detials))

            pendingIntent?.let {
                setContentIntent(pendingIntent)
            }

        }

        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, mBuilder.build())
        }

        return notificationId
    }

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