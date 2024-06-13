package com.brohit.show_phaze.ui.screen.authenticated.home.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.brohit.show_phaze.domain.model.user.UserModel
import com.brohit.show_phaze.ui.component.InScaffold
import com.brohit.show_phaze.ui.navigation.OnScreenAction
import com.brohit.show_phaze.ui.navigation.screen.ScreenRoute

sealed class ProfileScreenAction {
    class Logout(val onScreenAction: OnScreenAction) : ProfileScreenAction()
}


@Composable
fun ProfileScreen(
    route: ScreenRoute,
    viewModel: ProfileViewModel,
    onScreenAction: OnScreenAction,
) {
    ProfileScreenContent(
        route = route,
        onScreenAction = onScreenAction,
        state = viewModel.state.collectAsStateWithLifecycle().value,
        onAction = viewModel::handleAction,
        userModel = viewModel.userState
            .collectAsStateWithLifecycle(UserModel.empty()).value
    )
}

@Composable
fun ProfileScreenContent(
    userModel: UserModel,
    onAction: (ProfileScreenAction) -> Unit,
    route: ScreenRoute,
    onScreenAction: OnScreenAction,
    state: ProfileScreenState
) {
    InScaffold(
        screenRoute = route,
        onScreenAction = onScreenAction
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(it),
        ) {

        }
    }
}
