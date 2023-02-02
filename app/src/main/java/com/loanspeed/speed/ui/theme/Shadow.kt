package com.loanspeed.speed.ui.theme

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset

fun Modifier.shadow(
    color: Color,
    backgroundColor: Color = Color.White,
    shadowRadius: Dp,
    offset: DpOffset = DpOffset.Zero
) = composed (
    factory = {
        val paint = remember { Paint() }
        val backgroundPaint = remember {
            Paint().apply { this.color = backgroundColor }
        }
        val frameworkPaint = remember {
            paint.asFrameworkPaint()
        }
        Modifier.drawWithContent {
            this.drawIntoCanvas {
                val radius = shadowRadius.toPx()
                val shadowColor = color
                    .let { c ->
                        if (c.alpha == 1f) c.copy(alpha = .7f)
                        else c
                    }
                    .toArgb()
                val transparent = color
                    .copy(alpha = 0f)
                    .toArgb()
                frameworkPaint.color = transparent
                frameworkPaint.setShadowLayer(
                    radius,
                    offset.x.toPx(),
                    offset.y.toPx(),
                    shadowColor
                )
                it.drawRoundRect(
                    left = 0f,
                    top = 0f,
                    right = this.size.width,
                    bottom = this.size.height,
                    radiusX = radius,
                    radiusY = radius,
                    paint = paint
                )
                it.drawRoundRect(
                    left = 0f,
                    top = 0f,
                    right = this.size.width,
                    bottom = this.size.height,
                    radiusX = radius,
                    radiusY = radius,
                    paint = backgroundPaint
                )
                drawContent()
            }
        }
    }
)