package com.andriod.pushmessages

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.lang.StringBuilder

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

    }

    companion object {
        const val TAG = "@@MessagingService"
    }
}