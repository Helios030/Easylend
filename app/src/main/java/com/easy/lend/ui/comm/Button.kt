package com.easy.lend.ui.comm


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easy.lend.ui.theme.ButtonStyles
import com.easy.lend.ui.theme.RoundedCornerShapes
import com.easy.lend.ui.theme.colorWhite

@SuppressLint("ModifierParameter")
@Composable
fun TransparentRippleButton(
    onClick: () -> Unit,
    modifier: Modifier? = null,
    text: String,
    textStyle: TextStyle = TextStyle(
        color = colorWhite,
        fontSize = 18.sp,
    ),
    enabled: Boolean = true,
) {
        Button(
            onClick = onClick,
            modifier = (modifier ?: Modifier
                .fillMaxWidth()
                .height(54.dp))
                .background(
                    brush = ButtonStyles.solidGradientBrush,
                    shape = RoundedCornerShapes.large
                ),
            colors = ButtonStyles.transparentColors,
            elevation = null,
            contentPadding = PaddingValues(all = 0.dp),
            enabled = enabled,
        ) {
            Text(
                text = text,
                style = textStyle,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
}