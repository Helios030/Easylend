package com.loanspeed.speed.ui.comm

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.loanspeed.speed.R
import com.loanspeed.speed.ui.theme.RoundedCornerShapes
import com.loanspeed.speed.ui.theme.TextStyles.titleStyleBlack

/**
 * @Author Ben
 * @Date 2023/1/31 10:26
 * @desc:
 */

@Composable
fun TextDialog(
    title: String,
    contact: String,
    confirmClick: () -> Unit,
    dismissClick: () -> Unit,
    btnNum: Int=2,
    dialogState: MutableState<Boolean>
) {


    if (dialogState.value) {
//        AlertDialog(
//            onDismissRequest = { dialogState.value = false },
//            title = { Text(text = title) },
//            text = { Text(text = contact) },
//
//            confirmButton = {
//                TextButton(onClick = confirmClick) {
//                    Text(text = "YES")
//                }
//            },
//            dismissButton = {
//                TextButton(onClick = dismissClick) {
//                    Text(text = "NO")
//                }
//            },
//
//            )

        CommDialog(
            title = title,
            contact = {
                Text(text = contact)

            },
            confirmClick = {

                confirmClick.invoke()
                dialogState.value=false
            },
            dismissClick = {
                dismissClick.invoke()
                dialogState.value=false

            },
            isShowDialog = dialogState, btnNum = btnNum
        )

    }
}


@Composable
fun CommDialog(
    title: String,
    contact: @Composable (() -> Unit),
    confirmClick: (() -> Unit),
    dismissClick: (() -> Unit),
    btnNum: Int = 0,
    isShowDialog: MutableState<Boolean>

) {
    if (isShowDialog.value) {
        Dialog(onDismissRequest = { isShowDialog.value = false }) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShapes.large
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(10.dp)
                ) {

                    Text(text = title, style = titleStyleBlack)
//                    HorizontalLine(margin = PaddingValues(vertical = 10.dp))
                    Surface(content = contact, modifier = Modifier.padding(10.dp))




                    if (btnNum > 0) {
                        HorizontalLine(margin = PaddingValues(vertical = 10.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(35.dp)
                        ) {

                            if (btnNum > 1) {
                                TextButton(onClick = dismissClick) {
                                    Text(text = stringResource(R.string.No))
                                }
                                VerticalLine()
                            }
                            TextButton(onClick = confirmClick) {
                                Text(text = stringResource(R.string.Yes))
                            }
                        }

                    }


                }


            }


        }
    }


}


@Preview
@Composable
fun showCommDialog() {
    val isShowDialog = remember { mutableStateOf(true) }

//    TextDialog(
//        title = stringResource(id = R.string.exit_login),
//        contact = stringResource(id = R.string.log_out_tip),
//        confirmClick = {},
//        dismissClick = {},
//        dialogState = isShowDialog
//    )


//    CommDialog(
//        "选择你需要的验证码接受方式",
//        {
//            Row(
//                horizontalArrangement = Arrangement.SpaceAround,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier.clickable {
//                        "语音验证码".show()
//                        isShowDialog.value = false
//                    }) {
//                    Image(
//                        painter = painterResource(id = R.mipmap.phone_call),
//                        modifier = Modifier.size(100.dp),
//                        contentDescription = ""
//                    )
//                    Text(text = "语音验证码", modifier = Modifier.padding(top = 10.dp))
//                }
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier.clickable {
//                        "短信验证码".show()
//                        isShowDialog.value = false
//
//                    }) {
//                    Image(
//                        painter = painterResource(id = R.mipmap.phone_sms),
//                        modifier = Modifier.size(100.dp),
//                        contentDescription = ""
//                    )
//                    Text(text = "短信验证码", modifier = Modifier.padding(top = 10.dp))
//                }
//            }
//        },
//        {}, {}, isShowDialog = isShowDialog, btnNum = 2
//    )


//    CommButton(text = "test", onClick = { isShowDialog.value = true })


}