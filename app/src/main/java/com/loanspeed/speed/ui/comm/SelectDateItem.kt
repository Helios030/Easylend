package com.loanspeed.speed.ui.comm

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.loanspeed.speed.R
import com.loanspeed.speed.ui.theme.colorLine
import com.loanspeed.speed.ui.theme.colorPrimary
import kotlinx.coroutines.launch
import java.util.*

/**
 * @Author Ben
 * @Date 2023/2/2 10:19
 * @desc:
 */

//地址选择器
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectItem(
    itemTitle: String,
    selectValue: String,
    isShow: MutableState<Boolean>
    ) {

    val coroutineScope = rememberCoroutineScope()
    ItemBg {


        Text(text = itemTitle, color = colorPrimary, modifier = Modifier.fillMaxWidth(0.5F))

        Row(
            modifier = Modifier
                .fillMaxWidth(1F)
                .clickable {
                    isShow.value=true
//                    coroutineScope.launch {
//                        sheetState.show()
//                    }

                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = selectValue, color = colorLine)
            Image(painter = painterResource(id = R.mipmap.icon_arrow), contentDescription = "")
        }

    }

}

@Preview
@Composable
fun showSelectAddressItem() {


}





//时间选择器
@Composable
fun SelectDateItem(itemTitle: String, selectValue: MutableState<String>, onClick: () -> Unit) {
    ItemBg {
        Text(text = itemTitle, color = colorPrimary, modifier = Modifier.fillMaxWidth(0.5F))
        Row(
            modifier = Modifier
                .fillMaxWidth(1F)
                .clickable {
                    onClick.invoke()
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = selectValue.value, color = colorLine)
            Image(painter = painterResource(id = R.mipmap.icon_arrow), contentDescription = "")
        }

    }

}


@Preview
@Composable
fun showSelectItem() {

    val selectValue = remember { mutableStateOf("qingshuru") }

    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val mCalendar = Calendar.getInstance()
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()

    val mDatePickerDialog = DatePickerDialog(
        mContext, { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            selectValue.value = "$mDayOfMonth/${mMonth + 1}/$mYear"

        }, mYear, mMonth, mDay
    )
    SelectDateItem(itemTitle = "mignzi", selectValue = selectValue) {
        mDatePickerDialog.show()
    }

}