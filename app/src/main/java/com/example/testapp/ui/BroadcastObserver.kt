package com.example.testapp.ui

import java.util.*

class BroadcastObserver : Observable() {
    private fun triggerObservers() {
        setChanged()
        notifyObservers()
    }

    fun change() {
        triggerObservers()
    }
}