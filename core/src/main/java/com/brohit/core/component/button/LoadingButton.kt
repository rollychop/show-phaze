package com.brohit.core.component.button

import androidx.annotation.IntDef
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Target(AnnotationTarget.TYPE)
@IntDef(LoadingButtonType.TEXT, LoadingButtonType.OUTLINED, LoadingButtonType.DEFAULT)
annotation class LoadingButtonType {
    companion object {
        const val TEXT = 1
        const val OUTLINED = 2
        const val DEFAULT = 3
    }
}

@Composable
fun LoadingButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: @LoadingButtonType Int = LoadingButtonType.DEFAULT,
    loading: Boolean = false,
    enabled: Boolean = true,
    fillWidth: Boolean = false,
) {
    val transition = updateTransition(
        targetState = loading,
        label = "master transition",
    )
    val horizontalContentPadding by transition.animateDp(
        transitionSpec = {
            spring(stiffness = SpringStiffness)
        },
        targetValueByState = { toLoading -> if (toLoading) 12.dp else 24.dp },
        label = "button's content padding",
    )
    when (type) {
        LoadingButtonType.TEXT -> {
            InTextButton(
                enabled = enabled,
                onClick = onClick,
                modifier = modifier
                    .defaultMinSize(minWidth = 1.dp),
                contentPadding = PaddingValues(
                    horizontal = horizontalContentPadding,
                    vertical = 8.dp,
                ),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    LoadingContent(
                        loadingStateTransition = transition,
                        size = DpSize(20.dp, 20.dp)
                    )
                    PrimaryContent(
                        loadingStateTransition = transition,
                        text = text,
                        fillWidth = fillWidth
                    )
                }
            }
        }

        LoadingButtonType.OUTLINED -> {
            InOutlinedButton(
                enabled = enabled,
                onClick = onClick,
                modifier = modifier.defaultMinSize(minWidth = 1.dp),
                contentPadding = PaddingValues(
                    horizontal = horizontalContentPadding,
                    vertical = 8.dp,
                ),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    LoadingContent(
                        loadingStateTransition = transition,
                        size = DpSize(20.dp, 20.dp)
                    )
                    PrimaryContent(
                        loadingStateTransition = transition,
                        text = text,
                        modifier = Modifier,
                        fillWidth = fillWidth
                    )
                }
            }
        }

        LoadingButtonType.DEFAULT -> {
            InButton(
                enabled = enabled,
                onClick = onClick,
                modifier = modifier.defaultMinSize(minWidth = 1.dp),
                contentPadding = PaddingValues(
                    horizontal = horizontalContentPadding,
                    vertical = 8.dp,
                ),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    LoadingContent(
                        loadingStateTransition = transition,
                        size = DpSize(20.dp, 20.dp)
                    )
                    PrimaryContent(
                        loadingStateTransition = transition,
                        text = text,
                        fillWidth = fillWidth
                    )

                }
            }
        }

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun LoadingContent(
    size: DpSize,
    loadingStateTransition: Transition<Boolean>,
) {
    loadingStateTransition.AnimatedVisibility(
        visible = { loading -> loading },
        enter = fadeIn(),
        exit = fadeOut(
            animationSpec = spring(
                stiffness = SpringStiffness,
                visibilityThreshold = 0.10f,
            ),
        ),
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(size),
            color = LocalContentColor.current,
            strokeWidth = 1.5f.dp,
            strokeCap = StrokeCap.Round,
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun PrimaryContent(
    text: String,
    loadingStateTransition: Transition<Boolean>,
    modifier: Modifier = Modifier,
    fillWidth: Boolean = false,
) {
    loadingStateTransition.AnimatedVisibility(
        visible = { loading -> !loading },
        enter = fadeIn() + expandHorizontally(
            animationSpec = spring(
                stiffness = SpringStiffness,
                dampingRatio = Spring.DampingRatioMediumBouncy,
                visibilityThreshold = IntSize.VisibilityThreshold,
            ),
            expandFrom = Alignment.CenterHorizontally,
        ),
        exit = fadeOut(
            animationSpec = spring(
                stiffness = SpringStiffness,
                visibilityThreshold = 0.10f,
            ),
        ) + shrinkHorizontally(
            animationSpec = spring(
                stiffness = SpringStiffness,
                // dampingRatio is not applicable here, size cannot become negative
                visibilityThreshold = IntSize.VisibilityThreshold,
            ),
            shrinkTowards = Alignment.CenterHorizontally,
        ),
        modifier = modifier
    ) {
        Text(
            text = text,
            modifier = Modifier
                .then(if (fillWidth) Modifier.fillMaxWidth() else Modifier)
                // so that bouncing button's width doesn't cut first and last letters
                .padding(horizontal = 4.dp),
            textAlign = TextAlign.Center
        )
    }
}

// use same spring stiffness so that all animations finish at about the same time
private const val SpringStiffness = Spring.StiffnessMediumLow

@Preview
@Composable
private fun LoadingButtonPreview() {
    MaterialTheme {
        Surface {
            var loading by remember {
                mutableStateOf(false)
            }
            LoadingButton(
                text = "Submit",
                onClick = { loading = !loading },
                loading = loading,
                type = LoadingButtonType.OUTLINED
            )
        }
    }
}