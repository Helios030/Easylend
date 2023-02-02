package com.loanspeed.speed.repository.bean

abstract class ChooseItem {
    abstract fun getValue(): String
    fun isEmpty() = getValue().isEmpty()
}