package com.easy.lend.repository.bean

abstract class ChooseItem {
    abstract fun getValue(): String
    fun isEmpty() = getValue().isEmpty()
}