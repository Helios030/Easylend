package com.easy.lend.model



data class CallRecordModel(
        var call_time: String? = null,
        var phone: String? = null,
        var name: String? = null,
        var type: Int? = null, //类型，1呼入，2呼出，3未接通
        var call_duration: Long? = null,
        var is_connected: Int? = null //是否接通，1，接通，2未接通
)
