package com.loanspeed.speed.ui.comm

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loanspeed.speed.BuildConfig
import com.loanspeed.speed.R
import com.loanspeed.speed.ext.openUri
import com.loanspeed.speed.ui.theme.colorPrimary
import com.loanspeed.speed.util.slog

/**
 * @Author Ben
 * @Date 2023/1/30 10:34
 * @desc:协议同意按钮
 */


@Composable
fun AgreeButton(isCheck: MutableState<Boolean>,agreeStr:String=stringResource(R.string.agree_privacy),agreeUri: String=BuildConfig.PRIVACY_POLICY) {
    Row(
        verticalAlignment=Alignment.CenterVertically,
     ) {

        Checkbox(checked = isCheck.value, onCheckedChange = { isCheck.value = !isCheck.value })
        Column() {
            Text(text = stringResource(R.string.have_agree))
            Spacer(Modifier.height(5.dp))
            Text(text = agreeStr, color = colorPrimary, modifier = Modifier.clickable {
                openUri(agreeUri)
            })
        }

    }


}

@Preview
@Composable
fun showAgreeButton() {
    val isCheck = remember { mutableStateOf(false) }
    AgreeButton(isCheck = isCheck)
    isCheck.slog("按钮状态")


}





