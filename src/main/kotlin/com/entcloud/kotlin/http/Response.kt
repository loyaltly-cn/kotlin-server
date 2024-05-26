package com.entcloud.kotlin.http

import com.entcloud.kotlin.dto.ResponseBody
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun success() = ResponseEntity(ResponseBody("ok"),HttpStatus.OK)

fun success(message:String) = ResponseEntity(ResponseBody(message),HttpStatus.OK)

fun <T : Any> success(message: String, obj:T) = ResponseEntity(ResponseBody(message, obj),HttpStatus.OK)

fun teapot(message: String) = ResponseEntity(ResponseBody(message),HttpStatus.I_AM_A_TEAPOT)

fun gone(message: String) = ResponseEntity(ResponseBody(message),HttpStatus.GONE) //不存在

fun conflict(message: String) = ResponseEntity(ResponseBody(message),HttpStatus.CONFLICT) //冲突

fun noAcceptable(message: String) = ResponseEntity(ResponseBody(message),HttpStatus.NOT_ACCEPTABLE) //不接受

fun serverErr(message: String) = ResponseEntity(ResponseBody(message),HttpStatus.INTERNAL_SERVER_ERROR) //500