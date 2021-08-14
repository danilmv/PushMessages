package com.andriod.pushmessages

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService: FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        Log.d(TAG, "onNewToken() called with: p0 = $p0")
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        Log.d(TAG, "onMessageReceived() called with: p0 = $p0")
    }

    companion object{
        const val TAG = "@@MessagingService"
    }
}