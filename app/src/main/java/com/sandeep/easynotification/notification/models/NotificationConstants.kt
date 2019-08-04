package  com.sandeep.easynotification.notification.models

import androidx.core.app.NotificationCompat

enum class Priority(priority: Int) {
    DEFAULT(NotificationCompat.PRIORITY_DEFAULT),
    LOW(NotificationCompat.PRIORITY_LOW),
    MIN(NotificationCompat.PRIORITY_MIN),
    HIGH(NotificationCompat.PRIORITY_HIGH),
    MAX(NotificationCompat.PRIORITY_MAX)
}


const val GROUP_1 = "group-1"
const val GROUP_2 = "group-2"
const val GROUP_3 = "group-3"