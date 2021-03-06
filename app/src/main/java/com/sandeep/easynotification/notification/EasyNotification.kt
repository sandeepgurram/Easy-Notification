package  com.sandeep.easynotification.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.annotation.DrawableRes
import com.sandeep.easynotification.notification.models.Channel
import com.sandeep.easynotification.notification.models.Conversation
import com.sandeep.easynotification.notification.models.NotificationAction
import com.sandeep.easynotification.notification.models.NotificationConfig
import java.lang.ref.WeakReference

interface EasyNotification {

    var config: NotificationConfig

    fun notify(
        title: String,
        content: String,
        expandedText: String = "",
        pendingIntent: PendingIntent? = null,
        actions: ArrayList<NotificationAction>? = null
    ): Int

    fun notifyConversation(
        conversationList: ArrayList<Conversation>,
        pendingIntent: PendingIntent? = null,
        actions: ArrayList<NotificationAction>? = null
    ): Int

    fun notifyWithImage(
        title: String,
        content: String,
        @DrawableRes image: Int = 0,
        pendingIntent: PendingIntent? = null,
        actions: ArrayList<NotificationAction>? = null
    ): Int

    fun update(
        notificationId: Int,
        title: String,
        content: String,
        expandedText: String = "",
        pendingIntent: PendingIntent? = null,
        actions: ArrayList<NotificationAction>? = null
    )

    fun updateWithImage(
        notificationId: Int,
        title: String,
        content: String,
        @DrawableRes image: Int = 0,
        pendingIntent: PendingIntent? = null,
        actions: ArrayList<NotificationAction>? = null
    )


    fun remove(notificationId: Int)

    fun removeAll()

    companion object {

        var notificationId: Int = 100

        var channels: ArrayList<Channel>? = null
        var defaultChannel: Channel? = null

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        fun init(contextRef: WeakReference<Context>, channelsList: ArrayList<Channel>) {

            if (channelsList.size <= 0) {
                throw Throwable("Channels list is empty, add minimum one channel")
                return
            }

            channels = channelsList
            defaultChannel = channels!![0]
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                channelsList.forEach {
                    createNoticationChannelWith(
                        contextRef, it.channelId, it.name, it.description, it.importance.ordinal
                    )
                    if (it.isDefaulf)
                        defaultChannel = it
                }
            }
        }

        private fun createNoticationChannelWith(
            contextRef: WeakReference<Context>, channelId: String,
            name: String,
            descriptionText: String,
            importance: Int
        ) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId, name, importance).apply {
                    description = descriptionText
                }
                // Register the channel with the system
                val notificationManager: NotificationManager =
                    contextRef.get()?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }else{
                return
            }
        }
    }


}