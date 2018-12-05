package com.sandeep.easynotification

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.sandy.notifysample.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val channelList = arrayListOf(
            Channel("channel", "Channel 1", "Description of channel"),
            Channel("channel2", "Channel 2", "Description of channel 2"),
            Channel("channel3", "Channel 3", "Description of channel 3")
        )

        EasyNotification.init(this, channelList)

        channel_1_grp_1.setOnClickListener {
            compactChannelNotifier(CHANNEL_ID_1, GROUP_1).notify(
                "Hello",
                "Channel 1",
                "For more information about how to create a notification with these features and more, read Create a Notification.\n" +
                        "Notification actions\n" +
                        "Although it's not required, every notification should open an appropriate app activity when tapped. In addition to this default notification action, you can add action buttons that complete an app-related task from the notification (often without opening an activity), as shown in figure 9."
            )
        }

        channel_1_grp_1_summary.setOnClickListener {
            compactChannelNotifier(CHANNEL_ID_1, GROUP_1, true).notify(
                "Group Notification",
                "This is small summary",
                "This is group summary. This is group summary. This is group summary. This is group summary. This is group summary. This is group summary."
            )
        }


        var channel2Id = 0
        channel_2.setOnClickListener {
            channel2Id = channelNotifier(CHANNEL_ID_2).notify(
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
            channelNotifier(CHANNEL_ID_2).update(
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

        heads_up.setOnClickListener {
            headsUpNotifier().notify("Heads up notification", "this is content")
        }

        picture_notification.setOnClickListener {
            headsUpNotifier().notifyWithImage("Picture", "Picture notification", R.drawable.notification_big_picture)
        }

        fab.setOnClickListener { view ->
            simpleNotifier().removeAll()
        }

        conversation_notification.setOnClickListener {
            simpleNotifier().notifyConversation(
                arrayListOf(
                    Conversation("pp1", "content of 1"),
                    Conversation("pp2", "content of 2"),
                    Conversation("pp3", "content of 3")
                )
            )
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
