package com.easy.lend.model


/**
 * user_loan_info : {"loan_max_amount_desc":"Pinjaman tertinggi",
 *                   "loan_max_amount":"Rp 2,000,000",
 *                   "remind_tip":"jumiah yang di terima:",
 *                   "action":"start",
 *                   "button_words":"Pergi ke pinjaman",
 *                   "status":"ditolak",
 *                   "amount":"900000",
 *                   "loan_daycount":"10",
 *                   "order_no":"1903201055251266529864",
 *                   "end_repay_time":"",
 *                   "repay_day_des":""}
 * user_base_info : {"phone":"","slogen":""}
 * guest_phone : 081809319197
 */
class UserInfoModel(
        var user_loan_info: UserLoanInfoBean? = null,
        var user_base_info: UserBaseInfoBean? = null,
        var announcement_info: AnnouncementBean? = null,
        var guest_phone: String? = null) {
        data class UserLoanInfoBean(
            var loan_max_amount_desc: String? = null,
            var loan_max_amount: String? = null,
            var remind_tip: String? = null,
            var action: String? = null,
            var button_words: String? = null,
            var loan_status: String = "",
            var extension_msg: String = "",
            var repayment_button_text: String = "",
            var payment_method_state: String = "",
            var status: String? = null,
            var amount: String? = null,
            var loan_daycount: String? = null,
            var order_no: String? = null,
            var end_repay_time: String? = null,
            var repay_day_des: String? = null) 
        data class UserBaseInfoBean(
            var phone: String? = null,
//            2022年04月08日 添加是否需要活体检测 1 是开启 0 是关闭
            var is_license: Int? = null,
            var current_points: String? = null,
            var slogen: String? = null) 
        data class AnnouncementBean(
            var announcement_title: String = "",
            var announcement_content: String = "",
            var announcement_cancel: String = "",
            var announcement_agree: String = "")
}