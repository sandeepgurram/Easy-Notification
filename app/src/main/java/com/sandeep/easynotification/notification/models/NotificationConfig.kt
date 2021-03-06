package  com.sandeep.easynotification.notification.models

import android.os.Build
import androidx.annotation.DrawableRes
import com.sandeep.easynotification.R

class NotificationConfig private constructor(val builder: Builder) {

    @DrawableRes
    val icon: Int = builder.icon
    val sound: String? = builder.sound
    val cancellable = builder.cancellable
    val priority: Priority = builder.priority
    val channel = builder.channel
    val vibrationPattern = builder.vibrationPattern
    val vibrate = builder.vibrate
    val group = builder.group
    val isGroupSummary = builder.isGroupSummary
    val enableTicker = builder.enableTicker

    companion object {

        class Builder {

            /**
             * using SVG causes crash in few devices. This is know issue.
             * Suggest to use only PNG or don't use PNG :P
             */
            @DrawableRes
            internal var icon: Int = R.drawable.ic_notifier
            internal var sound: String? = null
            internal var cancellable = true
            internal var priority: Priority =
                Priority.DEFAULT
            internal var channel = ""
            internal var vibrationPattern = longArrayOf(100, 100, 100) //default vibrate pattern
            internal var vibrate = false
            internal var group = ""
            internal var enableTicker = true

            /**
             * For supporting in devices 4.x, group summary should be true
             * This is hack.
             */
            internal var isGroupSummary = Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP

            fun isGroupSummary(value: Boolean) = apply {
                this.isGroupSummary = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) value else true
            }

            fun enableTicker(value: Boolean) = apply {
                this.enableTicker = value
            }

            fun setGroup(groupName: String) = apply {
                this.group = groupName
            }

            fun setVibrationPattern(pattern: LongArray) = apply {
                this.vibrationPattern = pattern
            }

            fun setVibration(shouldVibrate: Boolean) = apply {
                this.vibrate = shouldVibrate
            }

            fun setIcon(@DrawableRes icon: Int) = apply {
                this.icon = icon
            }

            fun setSound(soundPath: String) = apply {
                this.sound = soundPath
            }

            fun setCancellable(cancellable: Boolean) = apply {
                this.cancellable = cancellable

            }

            fun setPriority(priority: Priority) = apply {
                this.priority = priority
            }

            fun setChannel(channel: String) = apply {
                this.channel = channel
            }

            fun build(): NotificationConfig {
                return NotificationConfig(this)
            }

        }
    }
}