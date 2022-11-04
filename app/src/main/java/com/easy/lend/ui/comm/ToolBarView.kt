package com.easy.lend.ui.comm

import android.app.ActionBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easy.lend.ui.theme.TextStyles.titleStyle
import com.easy.lend.ui.theme.colorPrimary
import com.easy.lend.ui.theme.colorWhite

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
