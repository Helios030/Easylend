package com.easy.lend.model


/**
 * first_name :
 * last_name :
 * age :
 * gender :
 * id_no :
 * job_category :
 * email :
 * education_degree :
 * family_province :
 * family_city :
 * family_area :
 * facebook_account :
 * residence_booklet_account :
 * family_town :
 * family_detail_address :
 * idcard_front :
 */
data class UserDataOneModel(
            var first_name: String = "",
            var last_name: String = "",
            var age: String = "",
            var gender: String = "",
            var id_no: String = "",
            var job_category: String = "",
            var email: String = "",
            var education_degree: String = "",
            var family_province: String = "",
            var family_city: String = "",
            var family_area: String = "",
            var facebook_account: String = "",
            var residence_booklet_account: String = "",
            var family_town: String = "",
            var family_detail_address: String = "",
            var idcard_front: String = "")
