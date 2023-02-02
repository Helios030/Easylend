package com.loanspeed.speed.ui.comm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loanspeed.speed.ui.theme.*
import com.loanspeed.speed.R

/**
 * @Author Ben
 * @Date 2023/2/1 16:05
 * @desc:
 */
@Composable
fun StepView(progress: Float) {


    Box(contentAlignment = Alignment.Center, modifier = Modifier.background(colorPrimary)) {
        Card(modifier = Modifier.padding(10.dp), shape = RoundedCornerShape(15.dp)) {

            Column(
                modifier = Modifier.padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 25.dp,
                    bottom = 10.dp
                )
            ) {
                LinearProgressIndicator(
                    //设置水平进度条当前进度颜色
                    color = colorPrimary,
                    //设置水平进度条总长度颜色
                    backgroundColor = colorFFCCCCCC,
                    //设置水平进度条当前进度
                    progress = progress,
                    modifier = Modifier
                        .background(colorPrimary, shape = RoundedCornerShape(15.dp))
                        .fillMaxWidth()
                        .height(10.dp),

                    )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(R.string.personal_information),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = stringResource(R.string.information),
                            textAlign = TextAlign.Center
                        )
                    }


                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(R.string.additional_information),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = stringResource(R.string.information),
                            textAlign = TextAlign.Center
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(R.string.bank_certification),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = stringResource(R.string.information),
                            textAlign = TextAlign.Center
                        )
                    }


                }


            }


        }


    }

}

@Preview
@Composable
fun showStepView() {
    StepView(.33F)
}