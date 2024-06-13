package com.brohit.show_phaze.ui.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.brohit.show_phaze.R

@Composable
fun BrandImage(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.brand_logo),
        contentDescription = null
    )
}