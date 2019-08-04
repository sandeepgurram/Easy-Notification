package  com.sandeep.easynotification.notification.implementation

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import android.text.TextUtils
import com.sandeep.easynotification.notification.EasyNotification
import com.sandeep.easynotification.notification.EasyNotification.Companion.notificationId
import com.sandeep.easynotification.notification.models.Conversation
import com.sandeep.easynotification.notification.models.NotificationAction
import com.sandeep.easynotification.notification.models.NotificationConfig


open class PushNotification(protected val context: Context,config : NotificationConfig) :
    EasyNotification {

    override var config: NotificationConfig = config

    private fun showNotification(id: Int, notification: Notification) {
        with(NotificationManagerCompat.from(context)) {
            notify(id, notification)
        }
    }

    private fun getBuilderWith(
        title: String, content: String, detials: String,
        pendingIntent: PendingIntent?,
        actions: ArrayList<NotificationAction>?
    ) =
        getBuilder(pendingIntent, actions).apply {
            setContentTitle(title)
            setContentText(content)
            setTicker("$title $content")

            if (!TextUtils.isEmpty(content))
                setStyle(NotificationCompat.BigTextStyle().bigText(detials))

            pendingIntent?.let {
                setContentIntent(pendingIntent)
            }

            actions?.forEach {
                if (!TextUtils.isEmpty(it.name)) {
                    addAction(it.icon, it.name, it.pendingIntent)
                }
            }
        }

    private fun getBuilderWith(
        conversationList: ArrayList<Conversation>,
        pendingIntent: PendingIntent?,
        actions: ArrayList<NotificationAction>?
    ) = getBuilder(pendingIntent, actions).apply {

        setTicker("${conversationList[0].title} ${conversationList[0].content}")

        val style = NotificationCompat.MessagingStyle(Person.Builder().setName(conversationList[0].title).build())
            .setConversationTitle(conversationList[0].content)
        conversationList.forEach {
            style.addMessage(it.content, 100.toLong(), Person.Builder().setName(it.title).build())
        }

        setStyle(style)

        pendingIntent?.let {
            setContentIntent(pendingIntent)
        }

        actions?.forEach {
            if (!TextUtils.isEmpty(it.name)) {
                addAction(it.icon, it.name, it.pendingIntent)
            }
        }
    }

    private fun getBuilderWith(
        title: String, content: String, image: Int, pendingIntent: PendingIntent?,
        actions: ArrayList<NotificationAction>?
    ) =
        getBuilder(pendingIntent, actions).apply {
            setContentTitle(title)
            setContentText(content)
            setTicker("$title $content")

            if (image != 0) {

                val displayImage = BitmapFactory.decodeResource(
                    context.resources, image
                )
                setLargeIcon(displayImage)
                setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(displayImage)
                        .bigLargeIcon(null)
                )

            }

        }

    open fun getBuilder(
        pendingIntent: PendingIntent?,
        actions: ArrayList<NotificationAction>?
    ) = NotificationCompat.Builder(context, config.channel).apply {

        setSmallIcon(config.icon)
        priority = config.priority.ordinal
        setAutoCancel(config.cancellable)
        if (config.vibrate && Build.VERSION.SDK_INT >= 21)
            setVibrate(config.vibrationPattern)

        setCategory(NotificationCompat.CATEGORY_MESSAGE)

        setGroup(config.group)
        setGroupSummary(config.isGroupSummary)

        pendingIntent?.let {
            setContentIntent(pendingIntent)
        }
        actions?.forEach {
            if (!TextUtils.isEmpty(it.name)) {
                addAction(it.icon, it.name, it.pendingIntent)
            }
        }

    }

    override fun notify(
        title: String,
        content: String,
        expandedText: String,
        pendingIntent: PendingIntent?,
        actions: ArrayList<NotificationAction>?
    ): Int {

        incrementNotificationCount()

        var mBuilder = getBuilderWith(title, content, expandedText, pendingIntent, actions)

        showNotification(notificationId, mBuilder.build())

        return notificationId
    }

    override fun notifyWithImage(
        title: String,
        content: String,
        image: Int,
        pendingIntent: PendingIntent?,
        actions: ArrayList<NotificationAction>?
    ): Int {
        incrementNotificationCount()

        var mBuilder =
            getBuilderWith(title, content, image, pendingIntent, actions)

        showNotification(notificationId, mBuilder.build())

        return notificationId
    }

    override fun notifyConversation(
        conversationList: ArrayList<Conversation>,
        pendingIntent: PendingIntent?,
        actions: ArrayList<NotificationAction>?
    ): Int {
        incrementNotificationCount()

        var mBuilder = getBuilderWith(conversationList, pendingIntent, actions)

        showNotification(notificationId, mBuilder.build())

        return notificationId
    }

    protected fun incrementNotificationCount() {
        notificationId++
    }


    override fun update(
        notificationId: Int,
        title: String,
        content: String,
        detials: String,
        pendingIntent: PendingIntent?,
        actions: ArrayList<NotificationAction>?
    ) {
        var mBuilder = getBuilderWith(title, content, detials, pendingIntent, actions)

        showNotification(notificationId, mBuilder.build())
    }

    override fun updateWithImage(
        notificationId: Int,
        title: String,
        content: String,
        image: Int,
        pendingIntent: PendingIntent?,
        actions: ArrayList<NotificationAction>?
    ) {
        var mBuilder = getBuilderWith(title, content, image, pendingIntent, actions)

        showNotification(notificationId, mBuilder.build())
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