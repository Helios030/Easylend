package com.easy.lend.ui.comm

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.easy.lend.R
import com.easy.lend.base.BaseDialog

@Composable
fun Loading(isLoading: Boolean) {
    if (isLoading) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
        BaseDialog(onDismissRequest = { }) {


            Card(
                modifier = Modifier.wrapContentSize(),
                shape = RoundedCornerShape(15.dp),
                elevation = 5.dp,
            ) {
                LottieAnimation(
                    composition = composition,
                    modifier = Modifier.size(100.dp).padding(10.dp),
                    iterations = LottieConstants.IterateForever,
                    alignment = Alignment.Center
                )

            }


        }
    }
}