package com.easy.lend.model



data class ContactModel(var name: String = "",
                        var numbers: ArrayList<PhoneModel> = ArrayList(),
//                       var pingyin: String = "",
                        var sortString: String = "#")  {
    fun addNumber(model: PhoneModel) {
//        if (numbers.isNullOrEmpty()) numbers = ArrayList()
        numbers.add(model)
    }
}