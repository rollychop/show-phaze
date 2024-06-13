package com.brohit.show_phaze.ui.screen.authenticated.home.profile

data class ProfileScreenState(
    val loading: Boolean = false,
    val error: String = "",


    val loggingOut: Boolean = false,
    val errorLoggingOut: String = ""
)