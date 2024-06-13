package com.brohit.show_phaze.ui.screen.auth_home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brohit.show_phaze.R
import com.brohit.show_phaze.ui.component.BrandImage
import com.brohit.show_phaze.ui.navigation.OnScreenAction
import com.brohit.show_phaze.ui.theme.ShowPhazeTheme

@Composable
fun AuthHomeScreen(
    onScreenAction: OnScreenAction
) {
    Scaffold {
        Box(
            modifier = Modifier
                .padding(it)
                .padding(top = 32.dp)
                .padding(horizontal = 32.dp),

            ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                BrandImage()
                Row(
                    horizontalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = "facebook"
                    )
                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(id = R.drawable.twitter),
                        contentDescription = "facebook"
                    )
                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(id = R.drawable.instagram),
                        contentDescription = "facebook"
                    )
                }
                Text(
                    text = "Ordering crew has never been this simple",
                    style = MaterialTheme.typography.displayMedium,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "We take your events as seriously as you do. Our crews are made up of the best freelancers in the area and we hand-pick the technicians for your event with purpose. Only the best will do, every time, no exceptions.",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )

                Button(onClick = {
                }) {
                    Text(text = "Build Your Event Now")
                }



                Column {

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xff6C61B0),
                            contentColor = Color.White
                        ),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(text = "Client Login")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = {

                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF2C5BAE),
                                contentColor = Color.White
                            ),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(text = "Client Login")
                        }
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = {

                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xff00A4CC),
                                contentColor = Color.White
                            ),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(text = "Client Login")
                        }
                    }
                }

            }
        }
    }
}

@Preview
@Composable
private fun AuthHomeScreenPreview() {
    ShowPhazeTheme {
        Surface {
            AuthHomeScreen {}
        }
    }
}