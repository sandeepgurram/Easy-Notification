package com.example.sandy.notifysample

import android.support.annotation.DrawableRes
import com.sandeep.easynotification.R

class NotificationConfig {

    private constructor()

    private constructor(builder: Builder) {
        this.builder = builder
        this.icon = builder.icon
        this.sound = builder.sound
        this.cancellable = builder.cancellable
        this.priority = Priority.DEFAULT
        this.channel = builder.channel
    }

    lateinit var builder: Builder

    @DrawableRes
    var icon: Int = R.drawable.ic_launcher_background
        private set(value) {}
    var sound: String? = null
        private set(value) {}
    var cancellable = true
        private set(value) {}
    var priority: Priority = Priority.DEFAULT
        private set(value) {}
    var channel = EasyNotification.CHANNEL_ID_1
        private set(value) {}


    companion object {

        class Builder {

            @DrawableRes
            internal var icon: Int = R.drawable.ic_launcher_background
            internal var sound: String? = null
            internal var cancellable = true
            internal var priority: Priority = Priority.DEFAULT
            internal var channel = EasyNotification.CHANNEL_ID_1

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
                val notificationConfig = NotificationConfig()

                return notificationConfig
            }

        }
    }
}