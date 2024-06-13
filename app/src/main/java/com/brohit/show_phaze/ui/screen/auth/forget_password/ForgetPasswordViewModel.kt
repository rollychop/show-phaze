package com.brohit.show_phaze.ui.screen.auth.forget_password

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(ForgetPasswordScreenState())
    val state = _state.asStateFlow()
}