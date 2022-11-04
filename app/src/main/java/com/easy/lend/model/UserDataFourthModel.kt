package com.easy.lend.model


class UserDataFourthModel(var bank_info_list: BankInfoListBean? = null){
    data class BankInfoListBean (var bank_info: UserDataFourModel? = null,
                                 var options: LinkedHashMap<String, String>? = null)
}