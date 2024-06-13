package com.brohit.show_phaze.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brohit.core.R
import com.brohit.show_phaze.ui.component.BrandImage
import com.brohit.show_phaze.ui.navigation.OnScreenAction
import com.brohit.show_phaze.ui.theme.ShowPhazeTheme
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(
    screenAction: OnScreenAction,
    viewModel: SplashViewModel
) {

    SplashContent()
    LaunchedEffect(key1 = Unit) {
        delay(1000)
        viewModel.load(screenAction)
    }
}

@Composable
private fun SplashContent() {
    Scaffold {
        Box(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 32.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BrandImage()
                Text(
                    text = "LET US TAKE CARE OF YOUR\n" +
                            "CREWING NEEDS",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}


@Preview
@Composable
private fun SplashScreenPreview() {
    ShowPhazeTheme {
        Surface {
            SplashContent()
        }
    }
}