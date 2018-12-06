package com.sandeep.easynotification.notification

import android.content.Context
import android.widget.RemoteViews
import com.sandeep.easynotification.notification.models.NotificationConfig

class NotificationFactory(val context: Context) {

    fun getNotification(config: NotificationConfig): PushNotification {
        val notification = PushNotification(context, config)
        return notification
    }

    fun getCustomNotification(
        config: NotificationConfig,
        notificationLayoutExpanded: RemoteViews,
        notificationLayout: RemoteViews,
        fullCustomised: Boolean = false
    ): CustomPushNotification {
        val notification = CustomPushNotification(
            context, config = config, notificationLayout = notificationLayout,
            notificationLayoutExpanded = notificationLayoutExpanded,
            fullCustomised = fullCustomised
        )
        return notification
    }

}
