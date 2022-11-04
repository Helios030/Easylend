package com.easy.lend.model

data class SearchIFSCBean(
    val bank_name: String,
    val branch: String,
    val district: String,
    val id: String,
    val ifsc_code: String,
    val micr_code: String,
    val state: String
)