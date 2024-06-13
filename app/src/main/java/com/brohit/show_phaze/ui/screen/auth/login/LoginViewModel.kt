package com.brohit.show_phaze.ui.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.brohit.show_phaze.domain.use_case.auth.LogInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase
) : ViewModel() {


    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    val inputState = LoginInputState()


    fun login(onLoggedIn: () -> Unit) {
        val username = inputState.usernameState.text
        val password = inputState.passwordState.text
        viewModelScope.launch {
            _state.update { s -> s.copy(loading = true) }
            logInUseCase(username, password).fold(
                onSuccess = {
                    _state.update { s -> s.copy(loading = false) }
                    onLoggedIn()
                },
                onFailure = {
                    _state.update { s -> s.copy(loading = false, error = "${it.message}") }
                }
            )

        }
    }
}