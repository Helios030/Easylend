package com.easy.lend.model



class VerificationCodeModel(var date: Any,
                            var jump_url: Int = 0,
                            var needInvitationCode: Boolean = false) {
    /**
     * data : https://www.accountkit.com/v1.0/basic/dialog/sms_login?app_id=393115071481921&redirect=https%3A%2F%2Ffintechvips.kmindo.com%2FaccountKitCallBack%2Fcallback&state=10fd0c1010127d92f75168722f8c04e2&fbAppEventsEnabled=true&country_code=62&locale=id_ID&debug=true&phone_number=085893571283
     * jump_url : 1
     * needInvitationCode : true
     */
}