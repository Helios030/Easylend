package com.easy.lend.model



/**
 * sms_upload : 0
 * phonebook_upload : 1
 * callinfo_upload : 0
 */
class CheckApplyStatusModel(var sms_upload: Int = 0,
                            var phonebook_upload: Int = 0,
                            var callinfo_upload: Int = 0)