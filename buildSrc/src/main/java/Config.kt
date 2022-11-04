package com.easy.config


object Config {
    const val compileSdk = 33
    const val appId = "com.easy.lend"
    const val minSdk = 23
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val packAppName = "Easylend"
    const val channel = "\"1\""
    const val onLineBaseUrl = "\"https://rupeelot.ml/\""
    const val betaBaseUrl = "\"https://rupeelot.ml/\""
    const val appName = "\"Easylend\""
    const val privacyPolicy = "\"https://rupeelot.ml//h5/html/privacy.html\""
    const val repaymentGuidelines = "\"https://rupeelot.ml/h5/html/repayment-data.html\""
    const val facebookAppId = "\"479819997255882\""
    const val areaCode = "\"+92\""
}

object Lib {
    const val compose_version = "1.3.0"
    const val hilt_version = "2.44"
    const val core_ktx = "androidx.core:core-ktx:1.7.0"
    const val lifecycle_runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"
    const val activity_compose = "androidx.activity:activity-compose:1.4.0"
    const val compose_ui = "androidx.compose.ui:ui:$compose_version"
    const val compose_ui_tooling = "androidx.compose.ui:ui-tooling-preview:$compose_version"
    const val compose_material = "androidx.compose.material:material:$compose_version"
    const val runtime_livedata = "androidx.compose.runtime:runtime-livedata:$compose_version"
    const val lifecycle_viewmodel_compose = "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1"
    const val accompanist_swipe_refresh = "com.google.accompanist:accompanist-swiperefresh:0.23.0"
    const val accompanist_pager = "com.google.accompanist:accompanist-pager:0.23.0"
    const val accompanist_pager_indicators = "com.google.accompanist:accompanist-pager-indicators:0.23.0"
    const val multidex = "com.android.support:multidex:1.0.3"
    const val auto_size = "me.jessyan:autosize:1.2.1"
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val gson = "com.google.code.gson:gson:2.8.8"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val work_runtime = "androidx.work:work-runtime-ktx:2.7.1"
    const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:4.9.3"
    const val lottie_compose = "com.airbnb.android:lottie-compose:5.0.2"
    const val compose_icon = "androidx.compose.material:material-icons-extended:$compose_version"
    //hilt
    const val hilt_android = "com.google.dagger:hilt-android:$hilt_version"
    const val hilt_kapt = "com.google.dagger:hilt-android-compiler:$hilt_version"
    const val hilt_navigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
    //    navigation
    const val navigation_compose = "androidx.navigation:navigation-compose:2.5.3"
    const val navigation_animation =
        "com.google.accompanist:accompanist-navigation-animation:0.24.6-alpha"
    //    security
    const val security_ktx = "androidx.security:security-crypto-ktx:1.1.0-alpha03"
     //权限申请
    const val permission = "com.github.getActivity:XXPermissions:13.6"


}