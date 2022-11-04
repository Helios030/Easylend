package com.easy.lend.ui.screen.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.easy.lend.BuildConfig
import com.easy.lend.R
import com.easy.lend.ext.show
import com.easy.lend.ext.str
import com.easy.lend.ext.toast
import com.easy.lend.rout.RoutPath
import com.easy.lend.ui.comm.Loading
import com.easy.lend.ui.theme.ComposeLoanTheme
import com.easy.lend.ui.theme.TextStyles.smallFieldValue


@Composable
fun LoginScreen(navController: NavController? = null, vm: LoginViewModel = hiltViewModel()) {

    val phone = remember { mutableStateOf("") }
    val code = remember { mutableStateOf("") }
    val loginState = vm.liveLoginBean.observeAsState()
    val isLoading = vm.isLoading.observeAsState()
    val time = vm.liveCodeStr.observeAsState()


    if (loginState.value != null) {
        toast(str(R.string.compose_loan_login_success))
        navController?.navigate(RoutPath.INDEX)
    }



    Loading(isLoading = isLoading.value == true)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            phone.value,
            onValueChange = { phone.value = it },
            singleLine = true,
            leadingIcon = { Text(text = "(${BuildConfig.AREA_CODE})", style = smallFieldValue) },
            label = { Text("Phone ") })

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(


            code.value,
            singleLine = true,
            onValueChange = {
                code.value = it
            },
            trailingIcon = {
                Text(
                    text = if(time.value.isNullOrBlank()) stringResource(id = R.string.compose_loan_send_code) else time.value!!,
                    style = smallFieldValue,
                    modifier = Modifier
                        .clickable {
                            R.string.compose_loan_send_code.show()
                            vm.sendCode(phone.value)
                        }
                        .padding(end = 10.dp)
                )
            },
            label = { Text("Code") },


            )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                vm.login(phone.value, code.value)
            }) {
            Text(text = stringResource(R.string.compose_loan_login))
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

