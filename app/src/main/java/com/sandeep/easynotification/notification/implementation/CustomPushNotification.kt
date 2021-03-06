package  com.sandeep.easynotification.notification.implementation

import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import android.widget.RemoteViews
import com.sandeep.easynotification.notification.models.NotificationAction
import com.sandeep.easynotification.notification.models.NotificationConfig


class CustomPushNotification(
    context: Context,
    private val notificationLayoutExpanded: RemoteViews?,
    private val notificationLayout: RemoteViews?,
    private val fullCustomised: Boolean,
    config: NotificationConfig
) : PushNotification(context, config) {

    /* override fun notify(
         title: String,
         content: String,
         expandedText: String,
         pendingIntent: PendingIntent?,
         actions: ArrayList<NotificationAction>?
     ): Int {

         incrementNotificationCount()

         var mBuilder = getBuilderWith(title, content, expandedText, pendingIntent, actions)

         mBuilder.apply {

             if (fullCustomised)
                 setStyle(NotificationCompat.DecoratedCustomViewStyle())

             notificationLayout?.let {
                 setCustomContentView(it)
             }
             notificationLayoutExpanded.let {
                 setCustomBigContentView(it)
             }
         }

         showNotification(EasyNotification.notificationId, mBuilder.build())

         return EasyNotification.notificationId

     }*/

    override fun getBuilder(
        pendingIntent: PendingIntent?,
        actions: ArrayList<NotificationAction>?
    ): NotificationCompat.Builder {
        val builder = super.getBuilder(pendingIntent, actions)

        builder.apply {

            if (!fullCustomised)
                setStyle(NotificationCompat.DecoratedCustomViewStyle())

            notificationLayout?.let {
                setCustomContentView(notificationLayout)
            }

            notificationLayoutExpanded?.let {
                setCustomBigContentView(notificationLayoutExpanded)
            }
        }

        return builder
    }


}