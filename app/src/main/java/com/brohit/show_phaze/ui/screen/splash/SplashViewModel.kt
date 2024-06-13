package com.brohit.show_phaze.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brohit.show_phaze.domain.use_case.session.CheckLoggedInSession
import com.brohit.show_phaze.ui.navigation.NavigationAction
import com.brohit.show_phaze.ui.navigation.OnScreenAction
import com.brohit.show_phaze.ui.navigation.screen.ScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkLoggedInSession: CheckLoggedInSession,
) : ViewModel() {
    fun load(action: OnScreenAction) {
        viewModelScope.launch {
            val sessionRoute = checkLoggedInSession.invoke()
            if (sessionRoute.isLoggedIn) {
                action(
                    NavigationAction.ChangeGraph(
                        ScreenRoute.Loading,
                        ScreenRoute.Authenticated
                    )
                )
            } else {
                action(
                    NavigationAction.ChangeGraph(
                        ScreenRoute.Loading,
                        ScreenRoute.Auth
                    )
                )
            }
        }
    }

}