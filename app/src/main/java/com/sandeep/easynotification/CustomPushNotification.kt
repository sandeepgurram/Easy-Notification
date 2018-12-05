package  com.sandeep.easynotification

import android.app.PendingIntent
import android.content.Context
import android.support.v4.app.NotificationCompat
import android.widget.RemoteViews


class CustomPushNotification(
    context: Context,
    private val notificationLayoutExpanded: RemoteViews?,
    private val notificationLayout: RemoteViews?,
    private val fullCustomised: Boolean
) : PushNotification(context) {

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