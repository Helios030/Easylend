package com.easy.lend.model



class UserDataModel(
    var personal_info_list: PersonalInfoListBean? = null) {
    data class PersonalInfoListBean(
        var user_info: UserDataOneModel? = null,
        var options: OptionsBean? = null) {
        /**
         * job_category_options : {"1":"私人雇员","2":"企业家","3":"国有企业员工","4":"公务员",
         * "5":"学习中的人","6":"警察","7":"退休","8":"专业医生律师","9":"失业的","10":"学生","11":"其他"}
         * education_degree_options : {"1":"小学","2":"初中","3":"高中","4":"大专","5":"大学","6":"硕士","7":"博士"}
         * gender_options : {"1":"男","2":"女"}
         */
        data class OptionsBean(
            var job_category_options: HashMap<String, String>? = null,
            var education_degree_options: HashMap<String, String>? = null,
            var gender_options: HashMap<String, String>? = null)
    }




}