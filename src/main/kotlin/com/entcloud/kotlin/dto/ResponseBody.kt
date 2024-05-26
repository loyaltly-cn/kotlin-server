package com.entcloud.kotlin.dto

data class ResponseBody (
    val message:String? = "ok",
    val content:Any? = "此接口由无锡恒云科技有限公司提供"
)