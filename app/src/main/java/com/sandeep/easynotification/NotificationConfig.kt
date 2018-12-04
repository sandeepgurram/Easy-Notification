package com.example.sandy.notifysample

import android.support.annotation.DrawableRes
import com.sandeep.easynotification.R

class NotificationConfig private constructor(val builder: Builder) {

    @DrawableRes
    var icon: Int = builder.icon
        private set(value) {}
    var sound: String? = builder.sound
        private set(value) {}
    var cancellable = builder.cancellable
        private set(value) {}
    var priority: Priority = builder.priority
        private set(value) {}
    var channel = builder.channel
        private set(value) {}
    var vibrationPattern = builder.vibrationPattern
        private set(value) {}
    var vibrate = builder.vibrate
        private set(value) {}

    companion object {

        class Builder {

            /**
             * using SVG causes crash in few devices(android 5 may be). This is know issue.
             * Suggest to use only PNG
             */
            @DrawableRes
            internal var icon: Int = R.drawable.ic_notifier
            internal var sound: String? = null
            internal var cancellable = true
            internal var priority: Priority = Priority.DEFAULT
            internal var channel = EasyNotification.CHANNEL_ID_1
            internal var vibrationPattern = longArrayOf(100, 100, 100) //default vibrate pattern
            internal var vibrate = false

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