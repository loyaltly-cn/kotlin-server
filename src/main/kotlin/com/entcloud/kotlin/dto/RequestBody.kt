package com.entcloud.kotlin.dto

data class QueryRequestBody<T>(
    val param: T?,
    val page: Int? = 0,
    val number: Int? = 1000,
)
