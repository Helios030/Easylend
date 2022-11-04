package com.easy.lend.ui.screen.index

import android.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.easy.lend.R
import com.easy.lend.ext.context
import com.easy.lend.ext.toast
import com.easy.lend.ui.comm.MenuDrawer
import com.easy.lend.ui.comm.ToolBarView
import com.easy.lend.ui.theme.ComposeLoanTheme
import com.easy.lend.ui.theme.TextStyles
import com.easy.lend.ui.theme.TextStyles.style_20_bold_colorPrimary
import com.easy.lend.ui.theme.TextStyles.style_button_text
import com.easy.lend.ui.theme.colorPrimary
import com.easy.lend.ui.theme.colorWhite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun IndexScreen(navController: NavController? = null, vm: IndexViewModel = hiltViewModel()) {


    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope: CoroutineScope = rememberCoroutineScope()


    Scaffold(
        modifier = Modifier.fillMaxSize(),
//        topBar = {
//            Column {
//                TopAppBar(title = {
//                    Text(stringResource(id = R.string.app_name), style = TextStyles.titleStyle)
//                }, navigationIcon = {
//                    IconButton(onClick = { coroutineScope.launch { if (drawerState.isOpen) drawerState.close() else drawerState.open() } }
//                    ) {
//                        Icon(Icons.Filled.Menu, "", tint = colorWhite)
//                    }
//
//
//                }
//
//                )
//            }
//        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
            ) {
                // 主要显示信息
                Box() {
                    Image(
                        painter = painterResource(id = R.mipmap.img_bg),
                        null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )

                    Card(
                        modifier = Modifier
                            .padding(start = 30.dp, top = 100.dp, end = 30.dp, bottom = 30.dp)

                            .fillMaxWidth(),
                        shape = RoundedCornerShape(15.dp),


                        ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {


                            Image(painter = painterResource(id = R.mipmap.icon_dashboard),
                                null, modifier = Modifier.padding(top = 20.dp))


                            Text(
                                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                                text = "Loan amount up to Rs 50,000",
                                style = style_20_bold_colorPrimary)

                            
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {


                            }) {
                              Text(text = "Go Loan Loan Loan", style = style_button_text)
                            }

                            Text(text = "i have read and agree")
                            Text(text = "privacy ")


                        }

                    }


                }


                //侧边栏
                MenuDrawer(drawerState)
            }


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