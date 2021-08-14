package com.andriod.pushmessages

import androidx.lifecycle.MutableLiveData

object DataManager {
    val message = MutableLiveData<String>()
    private val messagesList = mutableListOf<String>()

    fun addMessage(msg: String){
        messagesList.add(msg)
        message.postValue(msg)
    }
}