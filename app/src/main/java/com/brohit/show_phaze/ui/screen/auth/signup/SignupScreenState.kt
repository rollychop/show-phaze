package com.brohit.show_phaze.ui.screen.auth.signup

import com.brohit.show_phaze.domain.model.user.UserModel

data class SignupScreenState(
    val loading: Boolean = false,
    val error: String = "",
    val generatedUser: UserModel? = null
)