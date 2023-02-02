package com.loanspeed.speed.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

val RoundedCornerShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(15.dp)
)

/**
 * 虚线边框样式
 * @param step 间距
 */
data class DottedBorderShape(val step: Dp) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(Path().apply {
        val stepPx = with(density) { step.toPx() }
        val stepPxHalf = stepPx / 2

        val hStepsCount = (size.width / stepPx).roundToInt()
        val hActualStep = size.width / hStepsCount
        val hDotSize = Size(width = hActualStep / 2, height = 1f)
        for (i in 0 until hStepsCount) {
            addRect(
                Rect(
                    offset = Offset(x = i * hActualStep, y = 0f),
                    size = hDotSize
                )
            )
            addRect(
                Rect(
                    offset = Offset(x = i * hActualStep + stepPxHalf, y = size.height),
                    size = hDotSize
                )
            )
        }

        val vStepsCount = (size.height / stepPx).roundToInt()
        val vActualStep = size.height / vStepsCount
        val vDotSize = Size(width = 1f, height = vActualStep / 2)
        for (i in 0 until vStepsCount) {
            addRect(
                Rect(
                    offset = Offset(x = 0f, y = i * vActualStep + stepPxHalf),
                    size = vDotSize
                )
            )
            addRect(
                Rect(
                    offset = Offset(x = size.width, y = i * vActualStep),
                    size = vDotSize
                )
            )
        }
        close()
    })
}