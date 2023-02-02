package com.loanspeed.speed.ui.comm

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.loanspeed.speed.R
import com.loanspeed.speed.base.BaseDialog

@Composable
fun Loading(isLoading: Boolean?) {
    if (isLoading==true) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
        BaseDialog(onDismissRequest = { }) {
            Card(
                modifier = Modifier.wrapContentSize(),
                shape = RoundedCornerShape(10.dp),
                elevation = 5.dp,
            ) {
                LottieAnimation(
                    composition = composition,
                    modifier = Modifier
                        .size(150.dp)
                        .padding(1.dp),
                    iterations = LottieConstants.IterateForever,
                    alignment = Alignment.Center
                )

            }


        }
    }
}

@Preview
@Composable
fun shwoLoading(){
    Loading(isLoading = true)
}
