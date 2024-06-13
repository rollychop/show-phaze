package com.brohit.show_phaze.ui.screen.auth.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brohit.core.component.textfield.ConfirmPasswordState
import com.brohit.core.component.textfield.EmailState
import com.brohit.core.component.textfield.NameState
import com.brohit.core.component.textfield.PasswordState
import com.brohit.core.component.textfield.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import com.brohit.show_phaze.domain.use_case.auth.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


class SignupInputState {
    val nameState = NameState()
    val emailState = EmailState()
    val usernameState = TextFieldState(
        validator = { it.length > 5 },
        errorFor = { "Username is too short" }
    )
    val mobileNumberState = TextFieldState(
        validator = { it.length == 10 },
        errorFor = { "Invalid mobile number" }
    )
    val passwordState = PasswordState()
    val confirmPasswordState = ConfirmPasswordState(passwordState)

    var formAccepted by mutableStateOf(false)
        private set

    fun toggleFormAccepted(boolean: Boolean) {
        formAccepted = boolean
    }

    fun reset() {
        nameState.reset()
        emailState.reset()
        usernameState.reset()
        mobileNumberState.reset()
        passwordState.reset()
        confirmPasswordState.reset()
        formAccepted = false
    }

}


@HiltViewModel
class SignupViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SignupScreenState())
    val state = _state.asStateFlow()

    val inputState = SignupInputState()

    private fun signup() {
        val username = inputState.usernameState.text
        val password = inputState.passwordState.text
        val email = inputState.emailState.text
        val name = inputState.nameState.text
        val mobileNumber = inputState.mobileNumberState.text
        viewModelScope.launch {
            _state.update { s -> s.copy(loading = true, error = "") }
            registerUseCase.invoke(
                username = username,
                password = password,
                email = email,
                name = name,
                mobileNumber = mobileNumber
            ).fold(
                onSuccess = {
                    _state.update { s ->
                        s.copy(
                            loading = false,
                            generatedUser = it
                        )
                    }
                    inputState.reset()
                },
                onFailure = {
                    _state.update { s ->
                        s.copy(
                            loading = false,
                            error = "${it.message}"
                        )
                    }
                }
            )
        }
    }

    fun handleRegisterAction(action: SignupScreenAction) {
        when (action) {
            SignupScreenAction.HideDialog -> {
                _state.update { s -> s.copy(generatedUser = null) }
            }

            SignupScreenAction.Register -> {
                signup()
            }
        }
    }


}