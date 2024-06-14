package com.brohit.show_phaze.ui.screen.authenticated.home.dashboard

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brohit.core.component.textfield.InputField
import com.brohit.core.component.textfield.TextFieldState
import com.brohit.show_phaze.R
import com.brohit.show_phaze.ui.component.BrandImage
import com.brohit.show_phaze.ui.navigation.NavigationAction
import com.brohit.show_phaze.ui.navigation.OnScreenAction
import com.brohit.show_phaze.ui.navigation.screen.ScreenRoute
import com.brohit.show_phaze.ui.theme.ShowPhazeTheme


@Composable
@Suppress("UNUSED_PARAMETER")
fun DashboardScreen(
    route: ScreenRoute,
    onScreenAction: OnScreenAction,
    viewModel: DashboardViewModel,
) {
    DashboardScreenContent(
        onScreenAction = onScreenAction,
    )
}

enum class Steps(
    val icon: ImageVector,
    val title: String
) {
    Contact(Icons.Outlined.Person, "Contact Information"),
    Event(Icons.Outlined.CalendarToday, "Event Information"),
    Sessions(Icons.Outlined.AccessTime, "Sessions"),
    Summary(Icons.Outlined.Visibility, "Summary"),
}

@Composable
fun DashboardScreenContent(
    onScreenAction: OnScreenAction,
) {
    var currentTab by remember { mutableIntStateOf(1) }
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding()
                .padding(horizontal = 16.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.Top)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BrandImage(modifier = Modifier.weight(1f))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(text = "Guest")
                        Text(
                            modifier = Modifier.clickable {
                                onScreenAction(NavigationAction.Navigate(ScreenRoute.Auth))
                            },
                            text = "Login as Client",
                            color = Color(0xFF00EEC5),
                            style = MaterialTheme.typography.labelSmall,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.person),
                        contentDescription = "login",
                        modifier = Modifier
                            .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                            .size(40.dp)
                            .clip(CircleShape),
                    )
                }

            }
            Surface(
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                shape = MaterialTheme.shapes.small
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Steps.entries.forEachIndexed { index, steps ->
                        Surface(
                            color = if (currentTab == index) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(40.dp),
                            shape = CircleShape,
                            border = if (currentTab == index) null
                            else BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground),
                            tonalElevation = 2.dp,
                            shadowElevation = 2.dp

                        ) {
                            Icon(
                                modifier = Modifier
                                    .padding(8.dp),
                                imageVector = steps.icon,
                                contentDescription = steps.title
                            )
                        }
                        if (index != Steps.entries.lastIndex) {
                            Spacer(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(2.dp)
                                    .background(MaterialTheme.colorScheme.primary)
                            )
                        }
                    }


                }
            }
            AnimatedContent(
                targetState = currentTab,
                label = "",
                transitionSpec = {
                    slideInHorizontally { it } togetherWith slideOutHorizontally { -it }
                }
            ) { index ->
                val tab = Steps.entries[index]
                when (tab) {
                    Steps.Contact -> ContactScreen(
                        onBackClick = {
                            onScreenAction(NavigationAction.Navigate(ScreenRoute.Auth))
                        },
                        onNextClick = {
                            currentTab += 1
                        }
                    )

                    Steps.Event -> EventScreen(
                        onBackClick = {
                            currentTab -= 1
                        },
                        onNextClick = {
                            currentTab += 1
                        }
                    )

                    Steps.Sessions -> SessionsScreen(
                        onBackClick = {
                            currentTab -= 1
                        },
                        onNextClick = {
                            currentTab += 1
                        }
                    )

                    Steps.Summary -> SummaryScreen()
                }
            }


        }
    }
}

@Composable
fun SummaryScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Summary")
    }
}

@Composable
fun SessionsScreen(
    modifier: Modifier = Modifier,
    onNextClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    2.dp, MaterialTheme.colorScheme.primary,
                    MaterialTheme.shapes.small
                )
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Event Information",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineSmall
            )
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "First Name")
                InputField(
                    textFieldState = remember {
                        TextFieldState()
                    },
                    placeholder = "Type here...",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                    ),
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "Last Name")
                    InputField(
                        textFieldState = remember {
                            TextFieldState()
                        },
                        placeholder = "Type here...",
                        modifier = Modifier,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next,
                        ),
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "Last Name")
                    InputField(
                        textFieldState = remember {
                            TextFieldState()
                        },
                        placeholder = "Type here...",
                        modifier = Modifier,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next,
                        ),
                    )
                }
            }

            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Last Name")
                InputField(
                    textFieldState = remember {
                        TextFieldState()
                    },
                    placeholder = "Type here...",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                    ),
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Phone Number")
                InputField(
                    textFieldState = remember {
                        TextFieldState()
                    },
                    placeholder = "(XXX) XXX XXXX",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                    ),
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Email")
                InputField(
                    textFieldState = remember {
                        TextFieldState()
                    },
                    placeholder = "abc@xyz.com",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    ),
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Company Name")
                InputField(
                    textFieldState = remember {
                        TextFieldState()
                    },
                    placeholder = "Type here...",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                    ),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "Last Name")
                    InputField(
                        textFieldState = remember {
                            TextFieldState()
                        },
                        placeholder = "Type here...",
                        modifier = Modifier,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next,
                        ),
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "Last Name")
                    InputField(
                        textFieldState = remember {
                            TextFieldState()
                        },
                        placeholder = "Type here...",
                        modifier = Modifier,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next,
                        ),
                    )
                }
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onBackClick()
                    }
                    .clip(MaterialTheme.shapes.small)
                    .background(Color.White)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Back",
                    color = Color.Black,
                )
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(MaterialTheme.shapes.small)
                    .clickable {
                        onNextClick()
                    }
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color(0XFFFF00FF),
                                Color(0xFF8F00FF)
                            )
                        ),
                        MaterialTheme.shapes.small
                    )
                    .border(2.dp, Color.White, MaterialTheme.shapes.small)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun EventScreen(
    modifier: Modifier = Modifier,
    onNextClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    2.dp, MaterialTheme.colorScheme.primary,
                    MaterialTheme.shapes.small
                )
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Event Information",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineSmall
            )
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "First Name")
                InputField(
                    textFieldState = remember {
                        TextFieldState()
                    },
                    placeholder = "Type here...",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                    ),
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "Last Name")
                    InputField(
                        textFieldState = remember {
                            TextFieldState()
                        },
                        placeholder = "Type here...",
                        modifier = Modifier,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next,
                        ),
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "Last Name")
                    InputField(
                        textFieldState = remember {
                            TextFieldState()
                        },
                        placeholder = "Type here...",
                        modifier = Modifier,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next,
                        ),
                    )
                }
            }

            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Last Name")
                InputField(
                    textFieldState = remember {
                        TextFieldState()
                    },
                    placeholder = "Type here...",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                    ),
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Phone Number")
                InputField(
                    textFieldState = remember {
                        TextFieldState()
                    },
                    placeholder = "(XXX) XXX XXXX",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                    ),
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Email")
                InputField(
                    textFieldState = remember {
                        TextFieldState()
                    },
                    placeholder = "abc@xyz.com",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    ),
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Company Name")
                InputField(
                    textFieldState = remember {
                        TextFieldState()
                    },
                    placeholder = "Type here...",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                    ),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "Last Name")
                    InputField(
                        textFieldState = remember {
                            TextFieldState()
                        },
                        placeholder = "Type here...",
                        modifier = Modifier,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next,
                        ),
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "Last Name")
                    InputField(
                        textFieldState = remember {
                            TextFieldState()
                        },
                        placeholder = "Type here...",
                        modifier = Modifier,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next,
                        ),
                    )
                }
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onBackClick()
                    }
                    .clip(MaterialTheme.shapes.small)
                    .background(Color.White)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Back",
                    color = Color.Black,
                )
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(MaterialTheme.shapes.small)
                    .clickable {
                        onNextClick()
                    }
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color(0XFFFF00FF),
                                Color(0xFF8F00FF)
                            )
                        ),
                        MaterialTheme.shapes.small
                    )
                    .border(2.dp, Color.White, MaterialTheme.shapes.small)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun ContactScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    2.dp, MaterialTheme.colorScheme.primary,
                    MaterialTheme.shapes.small
                )
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Contact Information",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineSmall
            )
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "First Name")
                InputField(
                    textFieldState = remember {
                        TextFieldState()
                    },
                    placeholder = "Type here...",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                    ),
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Last Name")
                InputField(
                    textFieldState = remember {
                        TextFieldState()
                    },
                    placeholder = "Type here...",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                    ),
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Phone Number")
                InputField(
                    textFieldState = remember {
                        TextFieldState()
                    },
                    placeholder = "(XXX) XXX XXXX",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                    ),
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Email")
                InputField(
                    textFieldState = remember {
                        TextFieldState()
                    },
                    placeholder = "abc@xyz.com",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    ),
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Company Name")
                InputField(
                    textFieldState = remember {
                        TextFieldState()
                    },
                    placeholder = "Type here...",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                    ),
                )
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onBackClick()
                    }
                    .clip(MaterialTheme.shapes.small)
                    .background(Color.White)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Back",
                    color = Color.Black,
                )
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(MaterialTheme.shapes.small)
                    .clickable {
                        onNextClick()
                    }
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color(0XFFFF00FF),
                                Color(0xFF8F00FF)
                            )
                        ),
                        MaterialTheme.shapes.small
                    )
                    .border(2.dp, Color.White, MaterialTheme.shapes.small)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                )
            }
        }
    }
}

@Preview(
    heightDp = 1500
)
@Composable
private fun DashboardScreenPreview() {
    ShowPhazeTheme {
        Surface {
            DashboardScreenContent(
                onScreenAction = {}
            )
        }
    }
}
