package com.brohit.show_phaze.ui.screen.authenticated.home.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.brohit.show_phaze.domain.model.user.UserModel
import com.brohit.show_phaze.ui.component.InScaffold
import com.brohit.show_phaze.ui.navigation.OnScreenAction
import com.brohit.show_phaze.ui.navigation.screen.ScreenRoute


@Composable
fun DashboardScreen(
    route: ScreenRoute,
    onScreenAction: OnScreenAction,
    viewModel: DashboardViewModel,
) {
    DashboardScreenContent(
        route = route,
        onScreenAction = onScreenAction,
        userModel = viewModel.user
            .collectAsStateWithLifecycle(initialValue = UserModel.empty()).value
    )
}

@Composable
fun DashboardScreenContent(
    userModel: UserModel,
    route: ScreenRoute,
    onScreenAction: OnScreenAction,
) {
    InScaffold(
        screenRoute = route,
        onScreenAction = onScreenAction,
        topBar = {}
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.Top)
        ) {

        }
    }
}
