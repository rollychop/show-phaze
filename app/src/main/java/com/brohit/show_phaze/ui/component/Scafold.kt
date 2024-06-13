package com.brohit.show_phaze.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.LocaleList
import com.brohit.show_phaze.ui.navigation.NavigationAction
import com.brohit.show_phaze.ui.navigation.OnScreenAction
import com.brohit.show_phaze.ui.navigation.screen.BottomBarRoute
import com.brohit.show_phaze.ui.navigation.screen.BottomBarRoutes
import com.brohit.show_phaze.ui.navigation.screen.ScreenRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InScaffold(
    screenRoute: ScreenRoute,
    onScreenAction: OnScreenAction,
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {
        if (screenRoute is BottomBarRoute) {
            TopAppBar(title = {
                Text(
                    text = screenRoute.name.capitalize(LocaleList.current)
                )
            })
        }
    },
    bottomBar: @Composable () -> Unit = {
        if (screenRoute is BottomBarRoute) {
            BottomAppBar() {
                BottomBarRoutes.forEach {
                    NavigationBarItem(
                        selected = screenRoute == it,
                        onClick = { onScreenAction(NavigationAction.Navigate(it)) },
                        icon = { Icon(imageVector = it.icon, contentDescription = it.name) },
                        label = { Text(text = it.name) }
                    )
                }
            }
        }
    },
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        content = content
    )
}