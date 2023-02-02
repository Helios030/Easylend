package com.loanspeed.speed.ui.comm


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loanspeed.speed.ui.theme.*


@Composable
fun CommButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier? = Modifier.fillMaxWidth(),
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
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .background(colorPrimary,
                    shape = RoundedCornerShapes.large)

            ,
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
@Preview
@Composable
fun showCommButton(){
    CommButton("test",{})
}
