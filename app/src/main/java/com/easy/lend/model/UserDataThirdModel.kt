package com.easy.lend.model



class UserDataThirdModel(var idcard_hand: String? = null,
                         var work_license: String? = null,
                         var income_proof: String? = null) {
    override fun toString(): String {
        return "UserDataThirdBean(idcard_hand=$idcard_hand, work_license=$work_license, income_proof=$income_proof)"
    }
}