package com.easy.lend.model



data class LoanOptionsModel(var option_list: List<OptionListBean>? = null)  {
    /**
     * loan_option_id : 9
     * option_period : 7
     * option_min_value : 600000
     * option_max_value : 1200000
     * rate : 0.035
     * remind_tip : jumiah yang di terima:
     */
    data class OptionListBean(var loan_option_id: String? = null,
                              var option_period: String? = null,
                              var option_min_value: String? = null,
                              var option_max_value: String? = null,
                              var rate: String? = null,
                              var remind_tip: String? = null) 
}



