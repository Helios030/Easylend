package com.easy.lend.model



class DeferRepayModel(
        var loan_val: String = "",
        var extension_fee: String = "",
        var loan_daycount: String = "",
        var repay_end_time: String = "",
        var estimate_repay_end_time: String = "",
        var extension_number: String = "",
        var payment_code: String = ""
){
    override fun toString(): String {
        return "DeferRepayBean(loan_val='$loan_val', extension_fee='$extension_fee', loan_daycount='$loan_daycount', repay_end_time='$repay_end_time', estimate_repay_end_time='$estimate_repay_end_time', extension_number='$extension_number', payment_code='$payment_code')"
    }
}