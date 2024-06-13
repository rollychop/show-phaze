package com.brohit.show_phaze.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.brohit.show_phaze.ui.navigation.rememberNavigationController
import com.brohit.show_phaze.ui.navigation.screen.AuthRoute
import com.brohit.show_phaze.ui.navigation.screen.AuthenticatedRoute
import com.brohit.show_phaze.ui.navigation.screen.ScreenRoute
import com.brohit.show_phaze.ui.screen.auth.forget_password.ForgetPasswordScreen
import com.brohit.show_phaze.ui.screen.auth.login.LoginScreen
import com.brohit.show_phaze.ui.screen.auth.signup.SignupScreen
import com.brohit.show_phaze.ui.screen.authenticated.home.dashboard.DashboardScreen
import com.brohit.show_phaze.ui.screen.authenticated.home.profile.ProfileScreen
import com.brohit.show_phaze.ui.screen.authenticated.home.setting.SettingScreen
import com.brohit.show_phaze.ui.screen.splash.LoadingScreen
import com.brohit.show_phaze.ui.theme.ShowPhazeTheme

@Composable
fun MainScreenApp() {
    ShowPhazeTheme(darkTheme = true) {
        Surface {
            val controller = rememberNavigationController()
            NavHost(
                navController = controller.controller,
                startDestination = ScreenRoute.Loading.route,
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up)
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down)

                },
                popEnterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up)

                },
                popExitTransition = {
                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down)
                }
            ) {
                composable(ScreenRoute.Loading.route) {
                    LoadingScreen(
                        screenAction = controller::handleScreenAction,
                        viewModel = hiltViewModel()
                    )
                }
                navigation(
                    route = ScreenRoute.Auth.route,
                    startDestination = AuthRoute.Login.route
                ) {
                    composable(AuthRoute.Login.route) {
                        LoginScreen(
                            viewModel = hiltViewModel(),
                            onScreenAction = controller::handleScreenAction
                        )
                    }
                    composable(AuthRoute.Signup.route) {
                        SignupScreen(
                            route = AuthRoute.Signup,
                            viewModel = hiltViewModel(),
                            onScreenAction = controller::handleScreenAction
                        )
                    }
                    composable(AuthRoute.ForgetPassword.route) {
                        ForgetPasswordScreen(
                            route = AuthRoute.ForgetPassword,
                            viewModel = hiltViewModel(),
                            onScreenAction = controller::handleScreenAction
                        )
                    }
                }
                navigation(
                    route = ScreenRoute.Authenticated.route,
                    startDestination = AuthenticatedRoute.Home.route
                ) {
                    navigation(
                        route = AuthenticatedRoute.Home.route,
                        startDestination = AuthenticatedRoute.Home.Dashboard.route,
                        enterTransition = { fadeIn(tween(700)) },
                        exitTransition = { fadeOut(tween(700)) },
                        popExitTransition = { fadeOut(tween(700)) },
                        popEnterTransition = { fadeIn(tween(700)) }
                    ) {
                        composable(AuthenticatedRoute.Home.Dashboard.route) {
                            DashboardScreen(
                                route = AuthenticatedRoute.Home.Dashboard,
                                viewModel = hiltViewModel(),
                                onScreenAction = controller::handleScreenAction
                            )
                        }
                        composable(AuthenticatedRoute.Home.Setting.route) {
                            SettingScreen(
                                route = AuthenticatedRoute.Home.Setting,
                                viewModel = hiltViewModel(),
                                onScreenAction = controller::handleScreenAction
                            )
                        }
                        composable(AuthenticatedRoute.Home.Profile.route) {
                            ProfileScreen(
                                route = AuthenticatedRoute.Home.Profile,
                                viewModel = hiltViewModel(),
                                onScreenAction = controller::handleScreenAction
                            )
                        }
                    }
                }
            }
        }
    }
}

