
package  com.loanspeed.speed.net

import com.loanspeed.speed.BuildConfig
import com.loanspeed.speed.model.*
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {


    /**
     * 通过手机号获取验证码
     */
    @POST("loanspeed/info/verification_code")
    suspend fun getVerificationCode(@Body body: RequestBody): BaseModel<VerificationCodeModel>

    /**
     * 通过手机号获取语音验证码
     */
    @POST("loanspeed/info/voice_code")
    suspend fun getVoiceCode(@Body body: RequestBody): BaseModel<VerificationCodeModel>

    /**
     * 用户登录
     */
    @POST("loanspeed/info/login")
    suspend fun login(@Body body: RequestBody): BaseModel<LoginModel>

    /**
     * 上传流量来源
     */
    @POST("loanspeed/info/app_source")
    suspend fun uploadAppSource(@Body body: RequestBody): BaseModel<String>

    /**
     * 获取用户信息
     */
    @POST("loanspeed/info/index")
    suspend fun getUserInfo(@Body body: RequestBody): BaseModel<UserInfoModel>

    /**
     * 获取还款银行列表
     */
    @POST("loan/get_repay_bank_list")
    suspend fun getRepayBankList(@Body body: RequestBody): BaseModel<List<String>>

    /**
     * 获取还款数据
     */
    @POST("loanspeed/order/repayment_details")
    suspend fun getRepayDetails(@Body body: RequestBody): BaseModel<RepayModel>

    /**
     * 刷新还款数据
     */
    @POST("loan/refresh_repayment_data")
    suspend fun getRefreshRepayData(@Body body: RequestBody): BaseModel<RepayModel>


    /**
     * 获取展期数据
     * 服务器代码太多了，不使用展期接口
     *
     *
     */
//    @POST("order/extension_data")
    @POST("loanspeed/order/repayment_details")
    suspend fun getDeferRepayData(@Body body: RequestBody): BaseModel<DeferRepayModel>

    /**
     * 获取展期va码
     */
    @POST("loanspeed/order/repayment_details_extension")
    suspend fun getDeferRepayVaData(@Body body: RequestBody): BaseModel<DeferRepayModel>

    /**
     * 获取用户第一页数据
     */
    @POST("loanspeed/info/get_detail_info")
    suspend fun getUserDataOne(@Body body: RequestBody): BaseModel<UserDataModel>

    /**
     * 上传用户第一页数据
     */
    @POST("loanspeed/info/update_info")
    suspend fun upUserDataOne(@Body body: RequestBody): BaseModel<List<String>>

    /**
     * 上传照片
     */
    @POST("loanspeed/info/upload_photo")
    suspend fun upPicture(@Body body: RequestBody): BaseModel<PhotoModel>


    /**
     * 获取用户第二页数据
     */
    @POST("loanspeed/info/get_detail_info")
    suspend fun getUserDataTwo(@Body body: RequestBody): BaseModel<UserDataSecondModel>

    /**
     * 上传用户第二页数据
     */
    @POST("loanspeed/info/update_info")
    suspend fun upUserDataTwo(@Body body: RequestBody): BaseModel<List<String>>


    /**
     * 获取用户第四页数据
     */
    @POST("loanspeed/info/get_detail_info")
    suspend fun getUserDataFourth(@Body body: RequestBody): BaseModel<UserDataFourthModel>

    /**
     * 上传用户第四页数据
     */
    @POST("loanspeed/info/update_info")
    suspend fun upUserDataFourth(@Body body: RequestBody): BaseModel<List<String>>

    /**
     * 获取订单选项
     */
    @POST("loanspeed/order/get_order_options")
    suspend fun getLoanOptions(@Body body: RequestBody): BaseModel<LoanOptionsModel>

    /**
     * 获取确认订单详情
     */
    @POST("loanspeed/order/get_order_data")
    suspend fun getApplyPageData(@Body body: RequestBody): BaseModel<SureApplyModel>

    /**
     * 提交借款申请
     */
    @POST("loanspeed/order/place_order")
    suspend fun applyLoan(@Body body: RequestBody): BaseModel<List<String>>

    /**
     * 上传联系人
     */
    @POST("loanspeed/info/book_interface")
    suspend fun upContactsList(@Body body: RequestBody): BaseModel<List<String>>

    /**
     * 上传短信
     */
    @POST("loanspeed/info/sms_interface")
    suspend fun upSmsMessageList(@Body body: RequestBody): BaseModel<List<String>>

    /**
     * 上传通话记录
     */
    @POST("loanspeed/info/call_interface")
    suspend fun upCallRecordList(@Body body: RequestBody): BaseModel<List<String>>

    /**
     * 上传应用列表
     */
    @POST("loanspeed/info/apps_interface")
    suspend fun upInstallAppList(@Body body: RequestBody): BaseModel<List<String>>
    /**
     * 上传设备信息
     */
    @POST("loanspeed/info/device_info")
    suspend fun upPhoneState(@Body body: RequestBody): BaseModel<List<String>>


    @POST("loanspeed/order/living")
    suspend fun uploadLiveness(@Body body: RequestBody): BaseModel<Any>

    @POST("loanspeed/info/service_interface")
    suspend fun getCustomerService(@Body body: RequestBody): BaseModel<String>

    /**
     * 检查支付环境
     */
    @POST("loanspeed/info/check_status")
    suspend fun checkApplyStatus(@Body body: RequestBody): BaseModel<CheckApplyStatusModel>
    /**
     *获取地址列表
     */
    @POST("loanspeed/info/regions")
    suspend fun getRegionList(@Body body: RequestBody): BaseModel<List<RegionModel>>



    @POST("loanspeed/order/order_cancel")
    suspend fun orderCancel(@Body body: RequestBody): BaseModel<Boolean>


    @POST("loanspeed/info/album")
    suspend fun uploadAlbum(@Body body: RequestBody): BaseModel<String>


    companion object {
        val invite =   "${BuildConfig.BASE_URL}/static/appServer/invite.html"//    邀请好友
        val ktpImage =   "${BuildConfig.BASE_URL}/images/app/pic_shili_0.png"
        val handPhotoImage =   "${BuildConfig.BASE_URL}/images/app/pic_shili_1.png"
        val workPermitImage =   "${BuildConfig.BASE_URL}/images/app/pic_shili_2.png"
        val incomeImage =   "${BuildConfig.BASE_URL}/images/app/pic_shili_3.png"
        val privacyUrl = "${BuildConfig.BASE_URL}/h5/pages/privacy.html"
        val termsUrl = "${BuildConfig.BASE_URL}h5/pages/terms.html"
        val agreementUrl = "${BuildConfig.BASE_URL}/html/agreement.html"
        val helpUrl = "${BuildConfig.BASE_URL}/html/help.html"
    }


}