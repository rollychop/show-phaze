package com.brohit.show_phaze.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object AppState {

    var loaded by mutableStateOf(true)
        private set

    var signalLogout by mutableStateOf(false)
        private set

    fun unauthorizedLogin() {
        signalLogout = true
    }

    fun reset() {
        signalLogout = false
    }

    fun finishLoading() {
        loaded = true
    }

}