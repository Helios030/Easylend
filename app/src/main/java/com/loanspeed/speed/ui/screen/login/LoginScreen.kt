package com.loanspeed.speed.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.loanspeed.speed.BuildConfig
import com.loanspeed.speed.ext.show
import com.loanspeed.speed.ext.str
import com.loanspeed.speed.rout.RoutPath
import com.loanspeed.speed.ui.comm.AgreeButton
import com.loanspeed.speed.ui.comm.CommButton
import com.loanspeed.speed.ui.theme.ComposeLoanTheme
import com.loanspeed.speed.ui.theme.TextStyles.smallFieldValue
import com.loanspeed.speed.ui.theme.TextStyles.titleStyle
import com.loanspeed.speed.R
import com.loanspeed.speed.ui.comm.CommDialog
import com.loanspeed.speed.ui.comm.Loading

@Composable
fun LoginScreen(navController: NavController? = null, vm: LoginViewModel = hiltViewModel()) {

    val phone = remember { mutableStateOf("") }
    val code = remember { mutableStateOf("") }
    val time = vm.liveCodeStr.observeAsState()
    val isCheck = remember { mutableStateOf(false) }
    val isShowDialog = remember { mutableStateOf(false) }
    Loading(vm.isLoading.observeAsState().value)

//    登陆成功跳转
    if (vm.liveLoginBean.observeAsState().value != null) {
        navController?.navigate(RoutPath.INDEX)
    }

/**验证码接受方式弹框 start */
    CommDialog(
        stringResource(R.string.receive_code_fun),
        {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        vm.sendVoiceCode(phone.value)
                        isShowDialog.value = false
                    }) {
                    Image(
                        painter = painterResource(id = R.mipmap.phone_call),
                        modifier = Modifier.size(100.dp),
                        contentDescription = ""
                    )
                    Text(text = stringResource(R.string.voice_code), modifier = Modifier.padding(top = 10.dp))
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        vm.sendCode(phone.value)
                        isShowDialog.value = false

                    }) {
                    Image(
                        painter = painterResource(id = R.mipmap.phone_sms),
                        modifier = Modifier.size(100.dp),
                        contentDescription = ""
                    )
                    Text(text = stringResource(R.string.sms_code), modifier = Modifier.padding(top = 10.dp))
                }
            }
        },
        {}, {}, isShowDialog = isShowDialog,
    )
    /**验证码接受方式弹框 stop */



    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = R.mipmap.img_bg),
                modifier = Modifier.fillMaxWidth(),
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.mipmap.app_icon),
                    modifier = Modifier.size(100.dp),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(id = R.string.app_name), style = titleStyle)
            }
        }


        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .padding(top = 220.dp, start = 20.dp, end = 20.dp)


        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)

            ) {

                OutlinedTextField(
                    phone.value,
                    onValueChange = { phone.value = it },
                    singleLine = true,
                    leadingIcon = {
                        Text(
                            text = "(${BuildConfig.AREA_CODE})",
                            style = smallFieldValue
                        )
                    },
                    label = { Text(stringResource(id = R.string.telephone_number)) })

                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    code.value,
                    singleLine = true,
                    onValueChange = {
                        code.value = it
                    },
                    trailingIcon = {
                        Text(
                            text = if (time.value.isNullOrBlank()) stringResource(id = R.string.send_code) else time.value!!,
                            style = smallFieldValue,
                            modifier = Modifier
                                .clickable {
                                    if (time.value.isNullOrBlank()) {

                                        if (phone.value.isEmpty()) {
                                            (R.string.please_enter_phone).show()

                                        } else {
                                            isShowDialog.value=true
                                        }


                                    }
                                }
                                .padding(end = 10.dp)
                        )
                    },
                    label = { Text(stringResource(id = R.string.verification_code)) },
                )

                Spacer(modifier = Modifier.height(20.dp))

                AgreeButton(isCheck = isCheck)

                Spacer(modifier = Modifier.height(20.dp))

                CommButton(stringResource(R.string.loan_login), {

                    if (isCheck.value) {
                        vm.login(phone.value, code.value)
                    } else {
                        str(R.string.agree_this).show()
                    }

                })


            }


        }


    }


}


@Preview
@Composable
fun showLogin() {
    ComposeLoanTheme {
        LoginScreen(vm = LoginViewModel())
    }
}

