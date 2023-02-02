package com.loanspeed.speed.ui.screen.loan

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.loanspeed.speed.R
import com.loanspeed.speed.ext.show
import com.loanspeed.speed.model.UserInfoModel
import com.loanspeed.speed.rout.RoutPath
import com.loanspeed.speed.ui.comm.AgreeButton
import com.loanspeed.speed.ui.comm.CommButton
import com.loanspeed.speed.ui.screen.index.IndexViewModel
import com.loanspeed.speed.ui.theme.TextStyles
import com.loanspeed.speed.util.Slog
import com.loanspeed.speed.util.slog

/**
 * @Author Ben
 * @Date 2023/1/31 14:03
 * @desc:
 */

@Composable
fun NormalScreen(navController: NavController? = null, userinfo: UserInfoModel, isCheck: MutableState<Boolean>) {


    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = painterResource(id = R.mipmap.icon_dashboard),
            null, modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp),
            text = userinfo.loan_max_amount,
            style = TextStyles.style_20_bold_colorPrimary
        )


        CommButton(userinfo.button_words, {
//            if (userinfo.is_license==0) {
////                跳转活体
//            }else{

                navController!!.navigate(RoutPath.USER_DATA_FIRST)
//            }


        })


        AgreeButton(isCheck = isCheck)


    }

}


@Preview
@Composable
fun showNormalScreen() {

}