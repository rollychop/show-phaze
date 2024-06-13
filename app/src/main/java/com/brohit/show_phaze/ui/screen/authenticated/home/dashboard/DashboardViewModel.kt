package com.brohit.show_phaze.ui.screen.authenticated.home.dashboard

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.brohit.show_phaze.domain.use_case.user.GetLoggedUser
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    getLoggedUser: GetLoggedUser,
) : ViewModel() {

    val user = getLoggedUser()
}