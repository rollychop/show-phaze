package com.brohit.show_phaze.ui.screen.auth.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Support
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.brohit.core.component.button.LoadingButton
import com.brohit.core.component.text.ErrorText
import com.brohit.core.component.textfield.InputField
import com.brohit.show_phaze.ui.navigation.NavigationAction
import com.brohit.show_phaze.ui.navigation.OnScreenAction
import com.brohit.show_phaze.ui.navigation.screen.ScreenRoute


sealed class SignupScreenAction {
    data object Register : SignupScreenAction()
    data object HideDialog : SignupScreenAction()
}

@Composable
fun SignupScreen(
    route: ScreenRoute,
    viewModel: SignupViewModel,
    onScreenAction: OnScreenAction,
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    SignupScreenContent(
        route = route,
        onScreenAction = onScreenAction,
        state = state,
        inputState = viewModel.inputState,
        onSignupAction = viewModel::handleRegisterAction
    )
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun SignupScreenContent(
    onSignupAction: (SignupScreenAction) -> Unit,
    route: ScreenRoute,
    onScreenAction: OnScreenAction,
    state: SignupScreenState,
    inputState: SignupInputState,
) {

    Column(
        modifier = Modifier
            .systemBarsPadding(),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .imePadding()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LogoText()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Register",
                style = MaterialTheme.typography.headlineSmall
            )
            InputField(
                textFieldState = inputState.usernameState,
                placeholder = "Enter Username",
                label = "Username/Prefix*",
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Support,
                        contentDescription = "Username"
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )
            InputField(
                textFieldState = inputState.nameState,
                placeholder = "Enter Full Name",
                label = "Full Name*",
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Username"
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )
            InputField(
                textFieldState = inputState.emailState,
                placeholder = "Enter Email",
                label = "Email*",
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Username"
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            InputField(
                textFieldState = inputState.mobileNumberState,
                placeholder = "Enter Mobile Number",
                label = "Mobile*",
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Username"
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Phone
                ),
                transformText = { it.take(10) }
            )
            val showPassword = remember {
                mutableStateOf(false)
            }
            InputField(
                textFieldState = inputState.passwordState,
                placeholder = "Enter Password",
                label = "Password*",
                visualTransformation = if (showPassword.value) VisualTransformation.None
                else PasswordVisualTransformation(),
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
                        imageVector = Icons.Default.Password,
                        contentDescription = "Password"
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            val showConfirmPassword = remember {
                mutableStateOf(false)
            }
            InputField(
                textFieldState = inputState.confirmPasswordState,
                placeholder = "Confirm Password",
                label = "Confirm Password*",
                visualTransformation = if (showConfirmPassword.value) VisualTransformation.None
                else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    if (showConfirmPassword.value) {
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
                        imageVector = Icons.Default.Password,
                        contentDescription = "Password"
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                )
            )

            val uriHandler = LocalUriHandler.current
            val textColor = LocalContentColor.current
            val textStyle = MaterialTheme.typography.titleMedium.copy(color = textColor)
            val text = remember {
                buildAnnotatedString {
                    withStyle(
                        textStyle.toSpanStyle()
                    ) {
                        append("By clicking, I accept the ")
                        withAnnotation("URI", "https://www.quivertech.in/terms-of-service") {
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("terms of service")
                            }
                        }
                        append(" and ")
                        withAnnotation("URI", "https://www.quivertech.in/privacy-policy") {
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("privacy policy")
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                Checkbox(
                    checked = inputState.formAccepted,
                    onCheckedChange = inputState::toggleFormAccepted
                )
                ClickableText(
                    text = text,
                    onClick = {
                        text.getStringAnnotations(start = it, end = it).firstOrNull()
                            ?.let { annotation ->
                                when (annotation.tag) {
                                    "URI" -> uriHandler.openUri(
                                        annotation.item
                                    )

                                    else -> Unit
                                }
                            }
                    },
                    modifier = Modifier.padding(end = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            ErrorText(errorMessage = state.error)
            Spacer(modifier = Modifier.height(8.dp))

            LoadingButton(
                fillWidth = true,
                enabled = remember(
                    state,
                    inputState.usernameState.isValid,
                    inputState.passwordState.isValid,
                    inputState.confirmPasswordState.isValid,
                    inputState.nameState.isValid,
                    inputState.emailState.isValid,
                    inputState.mobileNumberState.isValid,
                    inputState.formAccepted
                ) {
                    derivedStateOf {
                        state.loading.not() &&
                                inputState.usernameState.isValid &&
                                inputState.passwordState.isValid &&
                                inputState.confirmPasswordState.isValid &&
                                inputState.formAccepted &&
                                inputState.nameState.isValid &&
                                inputState.emailState.isValid &&
                                inputState.mobileNumberState.isValid

                    }
                }.value,
                loading = state.loading,
                text = "Register",
                onClick = { onSignupAction(SignupScreenAction.Register) }
            )


            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Already have an account",
                    style = MaterialTheme.typography.titleSmall
                )
                TextButton(
                    onClick = {
                        onScreenAction(
                            NavigationAction.NavUp
                        )
                    }
                ) {
                    Text(text = "Login")
                }
            }
        }


    }

    state.generatedUser?.let {
        val clipboardManager = LocalClipboardManager.current
        AlertDialog(
            onDismissRequest = { onSignupAction(SignupScreenAction.HideDialog) },
            title = {
                Text(text = "User Registered")
            },
            text = {
                Text(text = buildAnnotatedString {
                    append("Name ")
                    val style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                    )
                    withStyle(style) {
                        append(it.name)
                    }
                    appendLine()
                    append("Username ")
                    withStyle(style) {
                        append(it.username)
                    }
                    appendLine()
                    append("Email ")
                    withStyle(style) {
                        append(it.email)
                    }
                    appendLine()
                    append("Mobile ")
                    withStyle(style) {
                        append(it.mobileNumber)
                    }
                })
            },
            dismissButton = {
                TextButton(
                    onClick = { onSignupAction(SignupScreenAction.HideDialog) }
                ) {
                    Text(text = "Dismiss")
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        clipboardManager.setText(AnnotatedString(it.username))
                    }
                ) {
                    Text(text = "Copy username")
                }
            }
        )
    }

}

@Composable
fun LogoText() {
    Column {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append("Quiver")
                }
                append(" ")
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
                    append("Tech")
                }
            },
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = "A unit of Invictus Digisoft Pvt. Ltd.",
            style = MaterialTheme.typography.titleSmall.copy(fontSize = 13.4.sp),
            modifier = Modifier.offset(y = -(10.dp), x = (32.dp))
        )
    }
}
