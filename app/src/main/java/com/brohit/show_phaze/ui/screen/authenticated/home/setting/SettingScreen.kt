package com.brohit.show_phaze.ui.screen.authenticated.home.setting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.brohit.show_phaze.domain.model.preference.UiMode
import com.brohit.show_phaze.ui.component.InScaffold
import com.brohit.show_phaze.ui.navigation.OnScreenAction
import com.brohit.show_phaze.ui.navigation.screen.ScreenRoute

@Composable
fun SettingScreen(
    route: ScreenRoute,
    viewModel: SettingViewModel,
    onScreenAction: OnScreenAction,
) {

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SettingScreenContent(
    uiMode: () -> UiMode,
    onChangeUiMode: (UiMode) -> Unit,
    route: ScreenRoute,
    onScreenAction: OnScreenAction,
    state: SettingScreenState
) {
    InScaffold(
        screenRoute = route,
        onScreenAction = onScreenAction
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {

        }
    }
}
