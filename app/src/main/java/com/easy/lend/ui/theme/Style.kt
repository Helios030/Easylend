package com.easy.lend.ui.theme

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

object ButtonStyles {
    val transparentColors: ButtonColors
        @Composable
        get() {
            return ButtonDefaults.buttonColors(
                backgroundColor = colorTransparent,
                contentColor = colorTransparent,
                disabledBackgroundColor = colorTransparent,
                disabledContentColor = colorTransparent,
            )
        }

    val solidGradientBrush = Brush.linearGradient(
        colors = listOf(colorPrimary, colorPrimaryVariant),
    )
    val transparentBrush = SolidColor(colorTransparent)
}

object TextStyles {
    val fieldTitle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = colorFF909090,
    )
    val smallFieldValue = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = colorPrimary,
    )
    val fieldValue = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = colorOnPrimary,
    )
    val fieldPlaceholder = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = colorFF909090,
    )

    val itemSelected = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = colorBlack,
    )
    val itemNormal = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = colorOnPrimary,
    )
    val itemUnEnabled = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = colorFFCCCCCC,
    )
    val titleStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = colorWhite,
    )

    val style_20_bold_colorPrimary = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = colorPrimary,
        textAlign = TextAlign.Center
    )
    val style_button_text = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = colorWhite,
    )
}