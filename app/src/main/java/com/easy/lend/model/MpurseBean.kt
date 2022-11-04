package com.easy.lend.model

data class MpurseBean(
    var repay_end_time: String,
    var repay_amount: String,
    var token: String,
    var order_id: String,
    var va_code: String,
    var ifsc: String,
    var dc_va_code: String
)