package com.loanspeed.speed.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

private val DarkColorPalette = darkColors(
    primary = colorPrimary,
    onPrimary = colorOnPrimary,
    primaryVariant = colorPrimaryVariant,
    secondary = colorSecondary,
    onSecondary = colorOnSecondary,
    secondaryVariant = colorSecondaryVariant,
    surface = colorBackground,
    background = colorBackground,
)

private val LightColorPalette = lightColors(
    primary = colorPrimary,
    onPrimary = colorOnPrimary,
    primaryVariant = colorPrimaryVariant,
    secondary = colorSecondary,
    onSecondary = colorOnSecondary,
    secondaryVariant = colorSecondaryVariant,
    surface = colorBackground,
    background = colorBackground,
)

@Composable
fun ComposeLoanTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = RoundedCornerShapes,
        content = {
            var modifierNew: Modifier = Modifier.then(
                Modifier.fillMaxSize()
            )
            modifierNew = modifierNew.then(Modifier.navigationBarsPadding())
            Column(
                modifier = modifierNew
            ) {
                Spacer(
                    modifier = Modifier
                        .windowInsetsTopHeight(WindowInsets.statusBars)
                        .fillMaxWidth()
                        .background(colors.primary)
                )
                content()
            }

        }
    )
}

