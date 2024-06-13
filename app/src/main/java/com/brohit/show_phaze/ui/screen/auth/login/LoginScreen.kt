package com.brohit.show_phaze.ui.screen.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.brohit.core.component.button.LoadingButton
import com.brohit.core.component.textfield.InputField
import com.brohit.show_phaze.ui.component.BrandImage
import com.brohit.show_phaze.ui.navigation.NavigationAction
import com.brohit.show_phaze.ui.navigation.OnScreenAction
import com.brohit.show_phaze.ui.navigation.screen.AuthRoute
import com.brohit.show_phaze.ui.theme.ShowPhazeTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onScreenAction: OnScreenAction,
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    LoginScreenContent(
        onScreenAction = onScreenAction,
        state = state,
        inputState = viewModel.inputState,
        onLoginClick = {
            onScreenAction(NavigationAction.Navigate(AuthRoute.Otp))
        }
    )


}

@Composable
fun LoginScreenContent(
    onLoginClick: () -> Unit,
    onScreenAction: OnScreenAction,
    state: LoginScreenState,
    inputState: LoginInputState,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .systemBarsPadding(),
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
                text = "LOGIN",
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Enter your credentials to continue.",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Email")
                InputField(
                    textFieldState = inputState.usernameState,
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
            val showPassword = remember {
                mutableStateOf(false)
            }
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Password")
                InputField(
                    textFieldState = inputState.passwordState,
                    placeholder = "Enter Password",
                    visualTransformation = if (showPassword.value) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        if (showPassword.value) {
                            IconButton(onClick = { showPassword.value = false }) {
                                Icon(
                                    imageVector = Icons.Filled.Visibility,
                                    contentDescription = "hide password"
                                )
                            }
                        } else {
                            IconButton(onClick = { showPassword.value = true }) {
                                Icon(
                                    imageVector = Icons.Filled.VisibilityOff,
                                    contentDescription = "show password"
                                )
                            }
                        }
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = "Password"
                        )
                    },
                )
            }
            Text(
                modifier = Modifier
                    .clickable {
                        onScreenAction(NavigationAction.Navigate(AuthRoute.ForgetPassword))
                    }
                    .align(Alignment.End),
                text = "Forget password",
                textDecoration = TextDecoration.Underline
            )

            LoadingButton(
                fillWidth = true,
                enabled = remember(
                    state,
                    inputState.usernameState.isValid,
                    inputState.passwordState.isValid,
                    inputState.forAccepted
                ) {
                    derivedStateOf {
                        state.loading.not()
                                && inputState.usernameState.isValid
                                && inputState.passwordState.isValid
                                && inputState.forAccepted

                    }
                }.value,
                loading = state.loading,
                text = "Login",
                onClick = onLoginClick
            )


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Don't have an account?",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "Register As Contractor",
                    style = MaterialTheme.typography.titleSmall,
                    textDecoration = TextDecoration.Underline
                )

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                HorizontalDivider(
                    modifier = Modifier.width(40.dp),
                    color = Color.White
                )
                Text(
                    text = "Or",
                )
                HorizontalDivider(
                    modifier = Modifier.width(40.dp),
                    color = Color.White
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = { },
                    shape = MaterialTheme.shapes.small,
                    contentPadding = PaddingValues(vertical = 8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "CONTRACTOR LOGIN",
                        maxLines = 1
                    )
                }
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = { },
                    shape = MaterialTheme.shapes.small,
                    contentPadding = PaddingValues(vertical = 8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "ADMIN LOGIN",

                        )
                }
            }
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

@Preview
@Composable
private fun LoginPreview() {
    ShowPhazeTheme {
        Surface {
            LoginScreenContent(
                onLoginClick = { /*TODO*/ },
                onScreenAction = {},
                state = LoginScreenState(),
                inputState = remember {
                    LoginInputState()
                }
            )
        }
    }
}

