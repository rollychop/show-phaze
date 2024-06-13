package com.brohit.show_phaze.ui.screen.auth.forget_password

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brohit.core.component.button.LoadingButton
import com.brohit.core.component.textfield.InputField
import com.brohit.core.component.textfield.TextFieldState
import com.brohit.show_phaze.ui.component.BrandImage
import com.brohit.show_phaze.ui.navigation.NavigationAction
import com.brohit.show_phaze.ui.navigation.OnScreenAction
import com.brohit.show_phaze.ui.navigation.screen.AuthRoute
import com.brohit.show_phaze.ui.navigation.screen.AuthenticatedRoute
import com.brohit.show_phaze.ui.navigation.screen.ScreenRoute
import com.brohit.show_phaze.ui.theme.ShowPhazeTheme

@Composable
fun ForgetPasswordScreen(
    route: ScreenRoute,
    viewModel: ForgetPasswordViewModel,
    onScreenAction: OnScreenAction,
) {
    ForgetPasswordScreenContent(
        route = route,
        onScreenAction = onScreenAction,
        state = viewModel.state.collectAsState().value
    )
}

@Composable
fun ForgetPasswordScreenContent(
    route: ScreenRoute,
    onScreenAction: OnScreenAction,
    state: ForgetPasswordScreenState
) {
    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .imePadding()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    BrandImage()
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "LET US TAKE CARE OF YOUR\n" +
                                "CREWING NEEDS",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Forget Password",
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Verify your email address. To receive OPT Code.",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.W300
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(text = "Email")
                        InputField(
                            textFieldState = remember {
                                TextFieldState()
                            },
                            placeholder = "Enter your email",
                            modifier = Modifier.fillMaxWidth(),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Email,
                                    contentDescription = "email"
                                )
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Email
                            ),
                        )
                    }


                    Spacer(modifier = Modifier.height(16.dp))




                    LoadingButton(
                        text = "VERIFY",
                        onClick = {
                            onScreenAction(
                                NavigationAction.ChangeGraph(
                                    ScreenRoute.Auth,
                                    AuthenticatedRoute.Home
                                )
                            )
                        }, fillWidth = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Go Back",
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {
                            onScreenAction(NavigationAction.NavUp)
                        }
                    )

                }


            }
        }
    }
}

@Preview
@Composable
private fun ForgetPasswordScreenPreview() {
    ShowPhazeTheme {
        Surface {
            ForgetPasswordScreenContent(
                route = AuthRoute.ForgetPassword,
                onScreenAction = {},
                state = ForgetPasswordScreenState()
            )
        }
    }
}
