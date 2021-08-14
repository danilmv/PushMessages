package com.andriod.pushmessages

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        DataManager.addMessage("onNewToken() called with: p0 = $p0")
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        DataManager.addMessage("onMessageReceived() called with: p0 = $p0")
    }

    companion object {
        const val TAG = "@@MessagingService"
    }
}