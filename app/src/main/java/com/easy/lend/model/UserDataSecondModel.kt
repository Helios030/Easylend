package com.easy.lend.model


class UserDataSecondModel(var additional_info_list: AdditionalInfoListBean? = null) {

    data class AdditionalInfoListBean(var additional_info: UserDataTwoModel? = null,
                                      var options: OptionsBean? = null,
                                      var tg_auth: TgAuthBean? = null){

        data class OptionsBean(
                var monthly_income: LinkedHashMap<String, String>? = null,
                var contacter_01_relationship: LinkedHashMap<String, String>? = null,
                var contacter_02_relationship: LinkedHashMap<String, String>? = null)

        data class TgAuthBean (
                var tg_gojek_url: String? = null,
                var tg_operator_url: String? = null,
                var tg_shopee_url: String? = null)
    }
}







