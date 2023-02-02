package com.loanspeed.speed.ui.screen.index


//https://blog.csdn.net/u011133887/article/details/122942728


import android.hardware.camera2.params.BlackLevelPattern
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.loanspeed.speed.BuildConfig
import com.loanspeed.speed.R
import com.loanspeed.speed.ext.initData
import com.loanspeed.speed.ext.openUri
import com.loanspeed.speed.ext.show
import com.loanspeed.speed.model.UserInfoModel
import com.loanspeed.speed.ui.comm.Loading
import com.loanspeed.speed.ui.comm.TextBody

import com.loanspeed.speed.ui.screen.loan.NormalScreen
import com.loanspeed.speed.ui.theme.ComposeLoanTheme
import com.loanspeed.speed.ui.theme.TextStyles
import com.loanspeed.speed.util.SpRepository
import com.loanspeed.speed.util.slog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun IndexScreen(
    navController: NavController? = null,
    vm: IndexViewModel = hiltViewModel()
) {


    val drawerState = rememberDrawerState(DrawerValue.Closed)

    val isAgree = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val isShowExitDialog = remember { mutableStateOf(false) }
    val isShowVersionDialog = remember { mutableStateOf(false) }

    Loading(vm.isLoading.observeAsState().value)


    initData {
        vm.getUserInfo()
    }

    val userInfo = vm.userInfoLiveData.observeAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { padding ->


            ModalDrawer(
                drawerState = drawerState,
                drawerShape = MaterialTheme.shapes.small,
                drawerContent = {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        DrawerHeader()
                        DrawerItem(
                            painter = painterResource(id = R.mipmap.icon_sign_query),
                            label = stringResource(R.string.help),
                            onClick = {
                                "help center".show()
                            }
                        )
                        DrawerItem(
                            painter = painterResource(id = R.mipmap.icon_version),
                            label = stringResource(id = R.string.version),
                            onClick = {
                                isShowVersionDialog.value = true
                            }
                        )
                        DrawerItem(
                            painter = painterResource(id = R.mipmap.icon_exit),
                            label = stringResource(id = R.string.exit_login),
                            onClick = {
                                isShowExitDialog.value = true
                            }
                        )
                    }
                },
                content = {
                    Box(
                        modifier = androidx.compose.ui.Modifier
                            .padding(padding)
                    ) {
                        // 主要显示信息
                        Box() {
                            Image(
                                painter = painterResource(id = R.mipmap.img_bg),
                                null,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentScale = ContentScale.FillWidth
                            )

                            Row(Modifier.padding(top = 40.dp, start = 20.dp, end = 20.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                IconButton(onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }

                                }, modifier = Modifier.size(40.dp)) {
                                    Image(
                                        painter = painterResource(id = R.mipmap.more),
                                        contentDescription = ""
                                    )
                                }

                                IconButton(onClick = {

                                    vm.getUserInfo()

                                }, modifier = Modifier.size(40.dp)) {
                                    Image(
                                        painter = painterResource(id = R.mipmap.refresh),
                                        contentDescription = ""
                                    )
                                }



                            }

                            Column(horizontalAlignment = Alignment.CenterHorizontally
                                , modifier = Modifier.padding(horizontal = 20.dp)
                            ) {
                                Card(
                                    modifier = Modifier
                                        .padding(
                                            start = 0.dp,
                                            top = 100.dp,
                                            end = 0.dp,
                                            bottom = 30.dp
                                        )
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(15.dp),
                                ) {
//                                    默认视图
                                    NormalScreen(navController,UserInfoModel(button_words="Go Loan",loan_max_amount="Loan amount up to Rs 50,000")
                                        , isAgree)

                                    userInfo.value?.let {

                                        when (it.action) {
                                            "start" -> {
                                                NormalScreen(navController,it, isAgree)
                                            }
                                            "change_card" -> {

                                            }
                                            "loan", "pass", "fail" -> {
                                            }
                                            "repay" -> {
                                            }
                                            else -> {
                                            }
                                        }
                                    }


                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                DrawerItem(
                                    "${stringResource(R.string.email)} ${userInfo.value?.guest_phone ?: ""}",
                                    painterResource(id = R.mipmap.icon_sign_phone),
                                    {openUri(BuildConfig.PRIVACY_POLICY) })

                                DrawerItem(
                                    painter = painterResource(id = R.mipmap.icon_sign_query),
                                    label = stringResource(R.string.help),
                                    onClick = {
                                        "help center".show()
                                    }
                                )



                            }

                        }


                    }

                }
            )


        }
    )


}


@Preview
@Composable
fun showIndexScreen() {
    ComposeLoanTheme {
        IndexScreen(vm = IndexViewModel())
    }
}

@Composable
private fun DrawerHeader(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(id = R.mipmap.icon_profile),
            null,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = stringResource(id = R.string.app_name), style = TextStyles.itemSelected)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "hello,+92 ${SpRepository.phone}", style = TextStyles.itemSelected)
    }
}


/**
 * 抽屉的Item
 */
@Composable
private fun DrawerItem(
    label: String,
    painter: Painter,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(10.dp)
            .clickable { onClick() }
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = Color.Unspecified

        )
        Spacer(Modifier.width(16.dp))
        TextBody(text = label)
    }
}
