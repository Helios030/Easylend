package com.easy.lend.model



/**
 * repay_end_time : 0000-00-00
 * repay_amount : 1200000
 * va_code : 7014023180016750
 * repay_bank : Permata
 * va_expire_time : Kode VA berlaku selama 6 jam
 * account_name : Cash One
 * payment_channel : Bluepay
 */
class RepayModel(
        var repay_end_time: String = "",
        var repay_amount: String = "",
        var va_code: String = "",
        var repay_bank_title: String = "",
        var repay_bank: String = "",
        var va_expire_time: String = "",
        var account_name: String = "",
        var payment_channel: String = "",
        var repay_warning: String = "",
        var enter_amount: String = "",)