package com.easy.lend.model

//data class MenuItem(val menuName:String,var isSelected:Boolean=false,val cityBeanResult: CityBeanResult?=null)
data class MenuItemModel(val menuName:String, var isSelected:Boolean=false, val cityModel: RegionModel?=null, val menuCode:String="0")