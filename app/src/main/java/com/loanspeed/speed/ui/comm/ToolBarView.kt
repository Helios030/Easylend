package com.loanspeed.speed.ui.comm

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.loanspeed.speed.ui.theme.TextStyles.titleStyle
import com.loanspeed.speed.ui.theme.colorWhite

/**
 * @Author Ben
 * @Date 2022/11/4 14:24
 * @desc:
 */

@Composable
fun ToolBarView(title: String, isShowBack:Boolean=false ,callback: () -> Unit) {


//    Row(
//        modifier = Modifier.fillMaxWidth().height(50.dp).background(colorPrimary),
//        horizontalArrangement = Arrangement.Center,
//        verticalAlignment =  Alignment.CenterVertically//设置垂直居中对齐
//    ) {
//        if(isShowBack){
//            IconButton(modifier =Modifier.align(Alignment.Start),onClick = {
//                callback()
//            }) {
//                Icon(Icons.Filled.ArrowBack, "")
//            }
//        }
//        Text(title,style = titleStyle)
//
//    }

    Column {

        TopAppBar(title = {
            Text(title,style = titleStyle)
        }, navigationIcon = {
            if(isShowBack){
                IconButton(onClick = {
                    callback()
                }) {
                    Icon(Icons.Filled.ArrowBack, "", tint = colorWhite)
                }
            }


        }

        )
    }
}
