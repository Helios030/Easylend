package com.loanspeed.speed.ui.comm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.loanspeed.speed.ui.theme.colorPrimary
import com.loanspeed.speed.ui.theme.colorWhite

/**
 * @Author Ben
 * @Date 2023/2/1 16:43
 * @desc:
 */


@Composable
fun CardInfoView(cardTitle:String,cardPadding:Dp=10.dp,boxPadding:Dp=10.dp,content: @Composable () -> Unit) {
    Card(shape = RoundedCornerShape(15.dp), modifier = Modifier
        .fillMaxWidth()
        .padding(cardPadding)) {
        Column(verticalArrangement = Arrangement.Center) {
        Box(
            Modifier
                .background(colorPrimary)
                .fillMaxWidth(),contentAlignment= Alignment.CenterStart) {
           Text(text = cardTitle, color = colorWhite, modifier = Modifier.padding(boxPadding))
        }

            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)) {
                Column() {
                    content.invoke()
                }

            }
        }
    }
}

@Preview
@Composable
fun showCardInfoView() {

    CardInfoView(content = { Text(text = "测试")}, cardTitle = "测试")

}