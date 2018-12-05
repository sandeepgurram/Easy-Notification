package  com.sandeep.easynotification

import android.support.v4.app.NotificationCompat

enum class Priority(priority: Int) {
    DEFAULT(NotificationCompat.PRIORITY_DEFAULT),
    LOW(NotificationCompat.PRIORITY_LOW),
    MIN(NotificationCompat.PRIORITY_MIN),
    HIGH(NotificationCompat.PRIORITY_HIGH),
    MAX(NotificationCompat.PRIORITY_MAX)
}

const val CHANNEL_ID_1 = "notification-1"
const val CHANNEL_ID_2 = "notification-2"

const val GROUP_1 = "group-1"
const val GROUP_2 = "group-2"
const val GROUP_3 = "group-3"