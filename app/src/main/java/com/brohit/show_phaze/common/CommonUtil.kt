package com.brohit.show_phaze.common

import android.content.res.Configuration
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red

object CommonUtil {
    fun fromHexOrDefault(
        hexColor: String,
        default: Color = Color.DarkGray
    ): Color {
        return try {
            val androidColor = android.graphics.Color.parseColor(hexColor)
            Color(
                red = androidColor.red,
                green = androidColor.green,
                blue = androidColor.blue,
                alpha = androidColor.alpha
            )
        } catch (e: Exception) {
            default
        }
    }
}

private fun prefixMobileNumber(mobileNumber: String): String {
    if (mobileNumber.length == 10) return "+91$mobileNumber"
    return mobileNumber
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
annotation class DPreview