package com.loanspeed.speed.ui.comm

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.loanspeed.speed.ext.show
import com.loanspeed.speed.model.MenuItemModel
import com.loanspeed.speed.ui.common.intervalClick
import com.loanspeed.speed.ui.theme.*
import com.loanspeed.speed.ui.theme.TextStyles.titleStyle
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PopupView(titile: String, list: List<MenuItemModel>?,currItem:MutableState<MenuItemModel>, isShow: MutableState<Boolean>) {


    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val coroutineScope = rememberCoroutineScope()

//    val isShow= remember { mutableStateOf(false) }




    if(isShow.value){
        LaunchedEffect(Unit, block = {
            coroutineScope.launch {
                sheetState.show()
            }
        })


    }else{
        LaunchedEffect(Unit, block = {
            coroutineScope.launch {
                sheetState.hide()
            }
        })



    }





    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp),
        sheetState = sheetState, sheetContent = {

            Column() {
                Box(
                    Modifier
                        .background(colorPrimary)
                        .fillMaxWidth(), contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = titile,
                        color = colorWhite,
                        style = titleStyle,
                        modifier = Modifier.padding(10.dp)
                    )
                }


                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    item {
                        list?.forEach {
                            Column(modifier = Modifier.clickable {
                                currItem.value = it
                                isShow.value=false
                            }) {

                                Text(text = it.menuName, modifier = Modifier.padding(10.dp))

                                Box(
                                    modifier = Modifier
                                        .background(colorPrimary)
                                        .fillMaxWidth()
                                        .height(0.5.dp)
                                )

                            }


                        }
                    }


                }


            }


        }, content = {
//处理后退事件，显示和隐藏必须用协程执行
            BackHandler(sheetState.isVisible) {
                coroutineScope.launch {
                    sheetState.hide()
                }

            }
//显示页面内容
        })



}


@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun showPopupView() {

    val genderSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val dateList = listOf(
                MenuItemModel("测试"),
                MenuItemModel("测试1"),
                MenuItemModel("测试2"),
                MenuItemModel("测试3"),
                MenuItemModel("测试4"),
                MenuItemModel("测试5"),
                MenuItemModel("测试6"),
                MenuItemModel("测试7"),
                MenuItemModel("测试8")
            )

    val currItem = remember {
        mutableStateOf(dateList.first())
    }

//    PopupView("测试", dateList,currItem,genderSheetState)

}
