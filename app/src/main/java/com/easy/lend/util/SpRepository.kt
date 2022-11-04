package com.easy.lend.util


typealias sp = PreferencesUtil

object SpRepository {

    internal  object  Key{
        val KEY_UUID="UUID"
        val KEY_TOKEN="KEY_TOKEN"
        val KEY_LANGUAGECODE="KEY_LANGUAGECODE"
        val KEY_IS_REFERRER="KEY_IS_REFERRER"
        val IS_SHOW_DIALOG="IS_SHOW_DIALOG"
        val IS_FIRST="IS_FIRST"
        val IS_FIRST_OPEN_PP="IS_FIRST_OPEN_PP"
        val PHONE="PHONE"
        val ID_PHOTO="ID_PHOTO"
    }

    var uuid: String
        get()= sp.getString(Key.KEY_UUID)
        set(value) = sp.saveValue(Key.KEY_UUID, value)

    var token: String
        get()= sp.getString(Key.KEY_TOKEN)
        set(value) = sp.saveValue(Key.KEY_TOKEN, value)

    var languageCode: String
        get()= sp.getString(Key.KEY_LANGUAGECODE, default ="ID")
        set(value) = sp.saveValue(Key.KEY_LANGUAGECODE, value)

    var isReferrer: Boolean
        get()= sp.getBoolean(Key.KEY_IS_REFERRER, default =true)
        set(value) = sp.saveValue(Key.KEY_IS_REFERRER, value)

    var isShowDialog: Boolean
        get()= sp.getBoolean(Key.IS_SHOW_DIALOG, default =true)
        set(value) = sp.saveValue(Key.IS_SHOW_DIALOG, value)



    var isFirst:Boolean
        get()= sp.getBoolean(Key.IS_FIRST, default =true)
        set(value) = sp.saveValue(Key.IS_FIRST, value)

    var isFirstOpenPP:Boolean
        get()= sp.getBoolean(Key.IS_FIRST_OPEN_PP, default =true)
        set(value) = sp.saveValue(Key.IS_FIRST_OPEN_PP, value)

    var phone:String
        get()= sp.getString(Key.PHONE)
        set(value) = sp.saveValue(Key.PHONE, value)

    var idPhoto:String
        get()= sp.getString(Key.ID_PHOTO)
        set(value) = sp.saveValue(Key.ID_PHOTO, value)












}