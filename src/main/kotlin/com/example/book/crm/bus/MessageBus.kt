package com.example.book.crm.bus

class MessageBus {
    fun sendEmailChangedMessage(userId: Int, newEmail: String) {
        println("Bus go-go sing. userID: {$userId}, newEmail: {$newEmail}")
    }
}
