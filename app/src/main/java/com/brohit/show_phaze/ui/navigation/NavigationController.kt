package com.brohit.show_phaze.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.withResumed
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.brohit.show_phaze.common.AppState
import com.brohit.show_phaze.ui.navigation.screen.AuthenticatedRoute
import com.brohit.show_phaze.ui.navigation.screen.BottomBarRoute
import com.brohit.show_phaze.ui.navigation.screen.NavigationRoute
import com.brohit.show_phaze.ui.navigation.screen.ScreenRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

sealed class NavigationAction {
    data object NavUp : NavigationAction()
    class Navigate(val screenRoute: ScreenRoute) : NavigationAction()
    class ChangeGraph(val fromScreenRoute: ScreenRoute, val toScreenRoute: ScreenRoute) :
        NavigationAction()
}

fun interface OnScreenAction {
    operator fun invoke(action: NavigationAction)
}


@Composable
fun rememberNavigationController(
    controller: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): NavigationController = remember(coroutineScope, controller) {
    NavigationController(coroutineScope, controller)
}


class NavigationController(
    private val coroutineScope: CoroutineScope,
    val controller: NavHostController,
) {

    init {
        coroutineScope.launch {
            snapshotFlow { AppState.signalLogout }.collectLatest {
                if (it) {
                    handleScreenAction(
                        NavigationAction.ChangeGraph(
                            ScreenRoute.Authenticated,
                            ScreenRoute.Auth
                        )
                    )
                    AppState.reset()
                }
            }
        }
    }

    private val currentRoute get() = controller.currentDestination?.route

    fun handleScreenAction(
        action: NavigationAction
    ) {

        if (action is NavigationAction.Navigate
            && action.screenRoute.route == currentRoute
        ) return

        coroutineScope.launch {
            controller.currentBackStackEntry?.withResumed {
                when (action) {
                    NavigationAction.NavUp -> controller.navigateUp()
                    is NavigationAction.Navigate -> when (action.screenRoute) {

                        is BottomBarRoute, is NavigationRoute -> {

                            controller.navigate(action.screenRoute.route) {
                                popUpTo(AuthenticatedRoute.Home.Dashboard.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }

                        else -> {
                            controller.navigate(action.screenRoute.route)
                        }
                    }

                    is NavigationAction.ChangeGraph -> {
                        controller.navigate(action.toScreenRoute.route) {
                            popUpTo(action.fromScreenRoute.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        }

    }

}