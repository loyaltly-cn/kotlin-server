package com.entcloud.kotlin.dto

data class LoginPasswordBody (
    val phone:String,
    val password:String
)


data class LoginCodeBody(
    val phone:String,
    val code:String,
    val auto:Boolean? = false
)

data class SuccessLoginBody(
    val data:Any,
    val token:String
)