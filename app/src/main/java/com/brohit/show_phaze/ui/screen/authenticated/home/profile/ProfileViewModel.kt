package com.brohit.show_phaze.ui.screen.authenticated.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.brohit.show_phaze.domain.use_case.auth.LogOutUseCase
import com.brohit.show_phaze.domain.use_case.user.GetLoggedUser
import com.brohit.show_phaze.ui.navigation.NavigationAction
import com.brohit.show_phaze.ui.navigation.OnScreenAction
import com.brohit.show_phaze.ui.navigation.screen.ScreenRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase,
    getLoggedUser: GetLoggedUser,
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileScreenState())
    val state = _state.asStateFlow()

    val userState = getLoggedUser()


    private fun logout(onScreenAction: OnScreenAction) {
        viewModelScope.launch {
            _state.update { s -> s.copy(loggingOut = true, errorLoggingOut = "") }
            logOutUseCase().fold(
                onSuccess = {
                    _state.update { s -> s.copy(loggingOut = false) }
                    onScreenAction(
                        NavigationAction.ChangeGraph(
                            ScreenRoute.Authenticated,
                            ScreenRoute.Auth
                        )
                    )
                },
                onFailure = {
                    _state.update { s ->
                        s.copy(
                            loggingOut = false,
                            errorLoggingOut = "${it.message}"
                        )
                    }
                }
            )
        }
    }

    fun handleAction(action: ProfileScreenAction) {
        when (action) {
            is ProfileScreenAction.Logout -> logout(action.onScreenAction)
        }
    }
}