package com.brohit.show_phaze.ui.screen.auth.post_forget_password_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brohit.core.component.button.LoadingButton
import com.brohit.show_phaze.ui.component.BrandImage
import com.brohit.show_phaze.ui.navigation.OnScreenAction
import com.brohit.show_phaze.ui.theme.ShowPhazeTheme

@Composable
fun PostForgetPasswordScreen(
    onScreenAction: OnScreenAction,
) {
    PostForgetPasswordScreenContent(
        onScreenAction = onScreenAction,
    )
}

@Composable
fun PostForgetPasswordScreenContent(
    onScreenAction: OnScreenAction,
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
                        text = "Successfully send you the new your password to your email address.",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xff00EEC5),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.W500

                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    LoadingButton(
                        text = "LOGIN NOW",
                        onClick = {

                        }, fillWidth = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))


                }


            }
        }
    }
}

@Preview
@Composable
private fun PostForgetPasswordScreenPreview() {
    ShowPhazeTheme {
        Surface {
            PostForgetPasswordScreenContent(
                onScreenAction = {},
            )
        }
    }
}
