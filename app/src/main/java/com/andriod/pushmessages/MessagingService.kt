package com.andriod.pushmessages

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.NotificationManagerCompat.IMPORTANCE_LOW
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        DataManager.addMessage("onNewToken() called with: p0 = $p0")
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        val msg = StringBuilder()
        msg.appendLine("onMessageReceived() called with:")
        p0.notification?.let {
            msg.appendLine("Title = ${it.title}")
            msg.appendLine("Body = ${it.body}")
            msg.appendLine("ChannelId = ${it.channelId}")
            msg.appendLine("EventTIme = ${it.eventTime}")
        }
        DataManager.addMessage(msg.toString())

        showSystemNotification(p0)
    }

    private fun showSystemNotification(remoteMessage: RemoteMessage) {
        val notificationManager = NotificationManagerCompat.from(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
            && notificationManager.getNotificationChannel(CHANNEL_ID) == null
        ) {
            val channel = NotificationChannelCompat.Builder(CHANNEL_ID, IMPORTANCE_LOW)
                .setName("MyChanel again")
                .setDescription("just a channel")
                .build()
            notificationManager.createNotificationChannel(channel)
        }

        val pendingIntent = PendingIntent.getActivities(this,
            0,
            arrayOf(Intent(this, MainActivity::class.java)),
            PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Re-pushed message: ${remoteMessage.notification?.title}")
            .setColor(Color.GRAY)
            .setSubText(remoteMessage.notification?.body)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setAutoCancel(true)
            .setCategory(Notification.CATEGORY_MESSAGE)
//            .setContentIntent(pendingIntent)
            .setCustomContentView(RemoteViews(packageName,
                R.layout.remoteview_notification)
                .apply {
                    setTextViewText(R.id.text_view, remoteMessage.notification?.body)
                    setOnClickPendingIntent(R.id.button_show_app, pendingIntent)
                })
            .setSmallIcon(R.drawable.ic_notification_icon_foreground)
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    companion object {
        const val TAG = "@@MessagingService"
        const val CHANNEL_ID = "1"
        const val NOTIFICATION_ID = 1
    }
}