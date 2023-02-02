package com.loanspeed.speed.ui.comm

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.loanspeed.speed.R
import com.loanspeed.speed.ui.theme.TextStyles.titleStyle

/**
 * @Author Ben
 * @Date 2023/2/1 10:57
 * @desc:
 */
@Composable
fun TextBody(text:String){

    Text(text, style = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ))
}

@Composable
fun TextTitle(text:String,mymodifier: Modifier){

    Text(text, style = titleStyle,modifier=mymodifier, textAlign = TextAlign.Center)
}

@Preview
@Composable
fun showTextBody(){

}