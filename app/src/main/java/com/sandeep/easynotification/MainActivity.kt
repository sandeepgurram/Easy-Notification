package com.sandeep.easynotification

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RemoteViews
import com.sandeep.easynotification.notification.EasyNotification.Companion.init
import com.sandeep.easynotification.notification.NotificationConfigDirectory
import com.sandeep.easynotification.notification.NotificationConfigDirectory.Companion.CONFIG_DEFAULT
import com.sandeep.easynotification.notification.NotificationFactory
import com.sandeep.easynotification.notification.models.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val channel = "channel"
        val channel2 = "channel2"
        val channel3 = "channel3"

        val channelList = arrayListOf(
            Channel("channel", "Channel 1", "Description of channel"),
            Channel(
                "channel2",
                "Channel 2",
                "Description of channel 2",
                isDefaulf = true
            ),
            Channel(
                "channel3",
                "Channel 2",
                "Description of channel 3",
                importance = ChannelImportance.IMPORTANCE_HIGH,
                isDefaulf = true
            ),
            Channel(
                "channel3",
                "Channel 3",
                "Description of channel 3"
            )
        )

        init(this, channelList)

        val simpleNotifier =
            NotificationFactory(this).getNotification(NotificationConfigDirectory().getConfig(CONFIG_DEFAULT))

        val compactNotification =
            NotificationFactory(this).getNotification(NotificationConfigDirectory().getCompactConfig(groupName = GROUP_1))

        val compactNotificationGroup = NotificationFactory(this).getNotification(
            NotificationConfigDirectory().getCompactConfig(
                groupName = GROUP_1,
                isGroupSummary = true
            )
        )

        val channel2Notifier =
            NotificationFactory(this).getNotification(NotificationConfigDirectory().getConfig(CONFIG_DEFAULT, channel2))

        val channel3Notifier =
            NotificationFactory(this).getNotification(NotificationConfigDirectory().getConfig(CONFIG_DEFAULT, channel3))

        val notificationLayout = RemoteViews("com.sandeep.easynotification", R.layout.notification_small)
        val notificationLayoutExpanded = RemoteViews("com.sandeep.easynotification", R.layout.notification_big)

        val customViewNotification =
            NotificationFactory(this).getCustomNotification(
                NotificationConfigDirectory().getConfig(CONFIG_DEFAULT, channel2),
                notificationLayout = notificationLayout,
                notificationLayoutExpanded = notificationLayoutExpanded
            )

        channel_1_grp_1.setOnClickListener {
            compactNotification.notify(
                "Hello",
                "Channel 1",
                "For more information about how to create a notification with these features and more, read Create a Notification.\n" +
                        "Notification actions\n" +
                        "Although it's not required, every notification should open an appropriate app activity when tapped. In addition to this default notification action, you can add action buttons that complete an app-related task from the notification (often without opening an activity), as shown in figure 9."
            )
        }

        channel_1_grp_1_summary.setOnClickListener {
            compactNotificationGroup.notify(
                "Group Notification",
                "This is small summary",
                "This is group summary. This is group summary. This is group summary. This is group summary. This is group summary. This is group summary."
            )
        }


        var channel2Id = 0
        channel_2.setOnClickListener {
            channel2Id = channel2Notifier.notify(
                "Hello",
                "Channel 2",
                "For more information about how to create a notification with these features and more, read Create a Notification.\n" +
                        "Notification actions\n" +
                        "Although it's not required, every notification should open an appropriate app activity when tapped. In addition to this default notification action, you can add action buttons that complete an app-related task from the notification (often without opening an activity), as shown in figure 9."
            )

            channel_2_update.visibility = View.VISIBLE
        }

        channel_2_update.visibility = View.GONE
        channel_2_update.setOnClickListener {
            channel2Notifier.update(
                channel2Id,
                "Update",
                "This is channle 2 update",
                "For more information about how to create a notification with these features and more, read Create a Notification.\n" +
                        "Notification actions\n" +
                        "Although it's not required, every notification should open an appropriate app activity when tapped. " +
                        "In addition to this default notification action, you can add action buttons that complete an app-related task from the notification " +
                        "(often without opening an activity), as shown in figure 9."
            )
        }

        val intent = Intent(this, SecondActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        heads_up.setOnClickListener {
            channel3Notifier.notify("Heads up notification", "this is content", pendingIntent = pendingIntent)
        }

        picture_notification.setOnClickListener {
            channel3Notifier.notifyWithImage(
                title = "Picture", content = "Picture notification", image = R.drawable.notification_img,
                actions = arrayListOf(
                    NotificationAction(
                        name = "Btn",
                        pendingIntent = pendingIntent
                    ),
                    NotificationAction(
                        name = "Btn2",
                        pendingIntent = pendingIntent,
                        icon = R.drawable.ic_notifier
                    ),
                    NotificationAction(
                        name = "Btn3",
                        pendingIntent = pendingIntent
                    ),
                    NotificationAction(
                        name = "Btn4",
                        icon = R.drawable.ic_notifier,
                        pendingIntent = pendingIntent
                    )
                )
            )
        }

        custom_notification.setOnClickListener {

            //            notificationLayout.setTextViewText(R.id.notification_title, "This is text")

            customViewNotification.notify("", "")
        }

        conversation_notification.setOnClickListener {
            simpleNotifier.notifyConversation(
                arrayListOf(
                    Conversation("pp1", "content of 1"),
                    Conversation("pp2", "content of 2"),
                    Conversation("pp3", "content of 3")
                )
            )
        }

        fab.setOnClickListener {
            simpleNotifier.removeAll()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
