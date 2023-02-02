package com.loanspeed.speed.ui.screen.user.first

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.loanspeed.speed.R
import com.loanspeed.speed.ext.initData
import com.loanspeed.speed.ext.map2MenuItem
import com.loanspeed.speed.ext.str
import com.loanspeed.speed.model.MenuItemModel
import com.loanspeed.speed.model.RegionModel
import com.loanspeed.speed.ui.comm.*
import com.loanspeed.speed.ui.theme.TextStyles
import com.loanspeed.speed.ui.theme.colorPrimary
import com.loanspeed.speed.ui.theme.colorWhite
import kotlinx.coroutines.launch
import java.util.*

/**
 * @Author Ben
 * @Date 2023/2/1 12:54
 * @desc:
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DataFirstScreen(
    navController: NavController? = null,
    vm: DataFirstViewModel = hiltViewModel()
) {
    initData {
        vm.apply {
            getCityList("0")
            getUserDataOne()
        }
    }
    Loading(vm.isLoading.observeAsState().value)
    val ADDRESS_LEVEL_1 = 1
    val ADDRESS_LEVEL_2 = 2
    val ADDRESS_LEVEL_3 = 3
    val ADDRESS_LEVEL_4 = 4
    val userInfo = vm.userLiveData.observeAsState()
    val userDataOne = userInfo.value?.personal_info_list?.user_info
    val userOptions = userInfo.value?.personal_info_list?.options


    val genderOption=userOptions?.gender_options?.map2MenuItem()
    val educationOption=userOptions?.education_degree_options?.map2MenuItem()
    val jobOption=userOptions?.job_category_options?.map2MenuItem()
    val province = remember { mutableStateOf(RegionModel(name="")) }


    val cityInfo = vm.cityLiveData.observeAsState()
    val firstName = remember { mutableStateOf(userDataOne?.first_name?: str(R.string.please_enter)) }
    val selectAddress = remember { mutableStateOf("${userDataOne?.family_province?: str(R.string.please_enter)} ${userDataOne?.family_city?:""} ${userDataOne?.family_city?:""} ${userDataOne?.family_town?:""}") }

    val coroutineScope = rememberCoroutineScope()
    val currIndex = remember { mutableStateOf(ADDRESS_LEVEL_1) }
    val currItem = remember { mutableStateOf(RegionModel(name="")) }

    val city = remember { mutableStateOf(RegionModel(name="")) }
    val area = remember { mutableStateOf(RegionModel(name="")) }
    val town = remember { mutableStateOf(RegionModel(name="")) }
    val birthday = remember { mutableStateOf(userDataOne?.birthday ?: str(R.string.please_choose)) }
    val selectGender = remember { mutableStateOf(MenuItemModel(userDataOne?.gender ?: str(R.string.please_enter))) }

    val genderPopState= remember {
        mutableStateOf(false)
    }
    val addressPopState= remember {
        mutableStateOf(false)
    }


    //    =========时间选择弹框
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
            birthday.value = "$mDayOfMonth/${mMonth + 1}/$mYear"

        }, mYear, mMonth, mDay
    )


    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .background(colorPrimary)
                    .padding(horizontal = 20.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.back), modifier = Modifier.size(30.dp),
                    contentDescription = ""
                )

                TextTitle(
                    text = stringResource(id = R.string.personal_information),
                    mymodifier = Modifier.fillMaxWidth()
                )
            }

        },


        ) { padding ->

        Column(Modifier.padding(padding)) {

            StepView(.33F)


            CardInfoView(stringResource(R.string.basic_info), content = {
                InputItem(
                    itemTitle = firstName.value,
                    text = firstName,
                    label = stringResource(id = R.string.please_enter)
                )
                Spacer(modifier = Modifier.height(10.dp))

                InputItem(
                    itemTitle = "First name",
                    text = firstName,
                    label = stringResource(id = R.string.please_enter)
                )
                Spacer(modifier = Modifier.height(10.dp))

                InputItem(
                    itemTitle = "First name",
                    text = firstName,
                    label = stringResource(id = R.string.please_enter)
                )
            })

            Spacer(modifier = Modifier.height(20.dp))


            CardInfoView(stringResource(R.string.basic_info), content = {

                SelectItem(
                    itemTitle = stringResource(id = R.string.current_province),
                    selectValue = selectAddress.value,
                    addressPopState
                )
                Spacer(modifier = Modifier.height(10.dp))
                SelectDateItem(itemTitle = stringResource(R.string.birthday), selectValue = birthday) {
                    mDatePickerDialog.show()
                }

                Spacer(modifier = Modifier.height(10.dp))

                SelectItem(
                    itemTitle = stringResource(id = R.string.gender),
                    selectValue = selectGender.value.menuName,
                    genderPopState
                )

                




            })

            Spacer(modifier = Modifier.height(20.dp))


        }
    }







    //======显示地址弹框
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    if(addressPopState.value){
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
    ModalBottomSheetLayout(sheetShape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp),
        sheetState = sheetState,
        sheetContent = {


            Column() {
                Box(
                    Modifier
                        .background(colorPrimary)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {

                    Column() {
                        Text(
                            text = stringResource(id = R.string.current_province),
                            color = colorWhite,
                            style = TextStyles.titleStyle,
                            modifier = Modifier.padding(10.dp)
                        )

                        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)) {

                            Text(text = province.value.name, color = colorWhite)
                            Text(text =  city   .value.name, color = colorWhite)
                            Text(text =  area   .value.name, color = colorWhite)
                            Text(text =  town   .value.name, color = colorWhite)


                        }
                    }



                }


                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    item {

                        if(cityInfo.value.isNullOrEmpty()){
                            vm.getCityList("0")
                            currIndex.value = ADDRESS_LEVEL_1
                            selectAddress.value="${province.value.name} ${city.value.name}"

                            LaunchedEffect(Unit, block = {
                                coroutineScope.launch {
                                    sheetState.hide()
                                }
                            })

                            return@item
                        }

                        cityInfo.value?.forEach {
                            Column(modifier = Modifier.clickable {

                                currItem.value=it


                                when (currIndex.value) {
                                    ADDRESS_LEVEL_1 -> {
                                        province.value = it
                                        currIndex.value = ADDRESS_LEVEL_2


                                    }
                                    ADDRESS_LEVEL_2 -> {
                                        currIndex.value = ADDRESS_LEVEL_3
                                        city.value = it

                                    }
                                    ADDRESS_LEVEL_3 -> {
                                        currIndex.value = ADDRESS_LEVEL_4
                                        area.value = it

                                    }
                                    ADDRESS_LEVEL_4 -> {
                                        town.value = it

                                    }

                                }
                                vm.getCityList(currItem.value.id)

                            }) {


                                Text(text = it.name, modifier = Modifier.padding(10.dp))


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


        },
        content = {
            BackHandler(sheetState.isVisible) {

                coroutineScope.launch {
                    sheetState.hide()
                }


            }
        })

//    普通弹框
    PopupView(titile = stringResource(id = R.string.gender), list = genderOption, currItem = selectGender, genderPopState)

}



@Preview
@Composable
fun showDataFirstScreen() {
    DataFirstScreen(vm = DataFirstViewModel())
}

