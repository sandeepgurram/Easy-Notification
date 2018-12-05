package com.example.sandy.notifysample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.support.annotation.DrawableRes
import com.sandeep.easynotification.Channel
import com.sandeep.easynotification.Conversation
import com.sandeep.easynotification.NotificationAction

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

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        fun init(context: Context, channelsList: ArrayList<Channel>) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                channelsList.forEach {
                    createNoticationChannelWith(
                        context, it.channelId, it.name, it.description, it.importance.ordinal
                    )
                }
            }
        }

        private fun createNoticationChannelWith(
            context: Context, channelId: String,
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
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

}