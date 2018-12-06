package com.sandeep.easynotification.notification

import android.os.Build
import android.text.TextUtils
import android.util.Log
import com.sandeep.easynotification.notification.models.NotificationConfig
import com.sandeep.easynotification.notification.models.Priority

class NotificationConfigDirectory {

    val TAG = "EasyNotification"

    fun getConfig(type: String, channelId: String = ""): NotificationConfig {

        return when (type) {
            CONFIG_DEFAULT -> {
                getDefaultConfig(channelId)
            }
            CONFIG_HEADS_UP -> {
                getHeadsUpConfig(channelId)
            }
            CONFIG_HIGH_PRIORITY -> {
                getHighPriority(channelId)
            }
            CONFIG_FIXED_NOTIFICATION -> {
                getFixedConfig(channelId)
            }
            else -> {
                Log.e(TAG, "Notification type is mismatched")
                getDefaultConfig(channelId)
            }
        }

        val config = NotificationConfig.Companion.Builder().apply {
            setChannel(getChannelID())
        }.build()

        return config
    }

    private fun getFixedConfig(channelId: String) = NotificationConfig.Companion.Builder().apply {
        setCancellable(false)
        setChannel(getChannelID(channelId))
        setPriority(Priority.HIGH)
        setVibration(true)
    }.build()

    private fun getHeadsUpConfig(channelId: String) = NotificationConfig.Companion.Builder().apply {
        setChannel(getChannelID(channelId))
        setPriority(Priority.HIGH)
        setVibration(true)
    }.build()

    private fun getHighPriority(channelId: String) = NotificationConfig.Companion.Builder().apply {
        setChannel(getChannelID(channelId))
        setPriority(Priority.HIGH)
    }.build()


    private fun getDefaultConfig(channelId: String) = NotificationConfig.Companion.Builder().apply {
        setChannel(getChannelID(channelId))
    }.build()

    /**
     * From Android 7.0 (API level 24) and higher, the system automatically builds a summary for your group using snippets of text from each notification.
     * <br>
     * To support older versions, which cannot show a nested group of notifications, you must create an extra notification that acts as the summary
     */
    fun getCompactConfig(
        channelId: String = "", channelID: String = "", groupName: String? = null,
        isGroupSummary: Boolean = false
    ) = NotificationConfig.Companion.Builder().apply {
        setChannel(getChannelID(channelId))
        if (!TextUtils.isEmpty(groupName)) {
            setGroup(groupName!!)
            isGroupSummary(isGroupSummary)
        }
    }.build()


    private fun getChannelID(channelID: String? = null): String {

        if (EasyNotification.channels?.size ?: 0 == 0)
            throw Throwable("Notification channels are not initialised")

        var channelId = ""

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (TextUtils.isEmpty(channelID)) {
                channelId = EasyNotification.defaultChannel!!.channelId
            } else {
                channelId = channelID!!
            }

        }
        return channelId
    }

    companion object {
        const val CONFIG_DEFAULT = "default-config"
        const val CONFIG_HEADS_UP = "heads-up-config"
        const val CONFIG_HIGH_PRIORITY = "high-priority-config"
        const val CONFIG_FIXED_NOTIFICATION = "fixed-notification-config"
        const val CONFIG_COMPACT = "compact-notification-config"
    }

}
