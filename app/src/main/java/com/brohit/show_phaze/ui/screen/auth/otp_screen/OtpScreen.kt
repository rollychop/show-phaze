package com.brohit.show_phaze.ui.screen.auth.otp_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brohit.core.component.button.LoadingButton
import com.brohit.show_phaze.ui.component.BrandImage
import com.brohit.show_phaze.ui.navigation.NavigationAction
import com.brohit.show_phaze.ui.navigation.OnScreenAction
import com.brohit.show_phaze.ui.navigation.screen.AuthenticatedRoute
import com.brohit.show_phaze.ui.navigation.screen.ScreenRoute
import com.brohit.show_phaze.ui.theme.ShowPhazeTheme

@Composable
fun OtpScreen(
    onScreenAction: OnScreenAction
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
                        text = "Confirm It's You",
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "We have send an OPT to your \n" +
                                "phone number: +1 234 ***** 3.",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Enter the OTP to continue.",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.align(Alignment.Start),
                        fontWeight = FontWeight.Bold
                    )
                    var otp by rememberSaveable {
                        mutableStateOf("")
                    }
                    BasicTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = otp.take(5),
                        onValueChange = {
                            if (it.length <= 5) {
                                otp = it
                            }
                        },
                        decorationBox = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                repeat(5) {
                                    val char: String = otp.getOrNull(it)?.toString() ?: ""
                                    Text(
                                        text = char,
                                        style = MaterialTheme.typography.titleLarge,
                                        modifier = Modifier
                                            .width(48.dp)
                                            .height(60.dp)
                                            .border(
                                                1.dp,
                                                MaterialTheme.colorScheme.onBackground,
                                                MaterialTheme.shapes.medium
                                            )
                                    )
                                    if (it != 4) {
                                        Text(text = "-")
                                    }

                                }
                            }
                        },
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "01:30",
                        modifier = Modifier,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        modifier = Modifier.clickable {
                        },
                        text = "Didn’t receive the OTP? Resend Code",
                        style = MaterialTheme.typography.titleMedium,
                        textDecoration = TextDecoration.Underline,
                        color = Color(0x80FFFFFF)
                    )



                    LoadingButton(
                        text = "CONTINUE",
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
private fun OptScreenPrev() {
    ShowPhazeTheme {
        Surface {
            OtpScreen(onScreenAction = {})
        }
    }
}