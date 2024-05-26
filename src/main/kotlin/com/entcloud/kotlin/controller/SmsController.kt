package com.entcloud.kotlin.controller

import com.entcloud.kotlin.dto.ResponseBody
import com.entcloud.kotlin.service.SmsService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("sms")
@Tag(name = "阿里云短信API")
class SmsController (val service:SmsService){

    @Operation(description = "直接传入手机号就行 不需要{} 也不需要key")
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun sms(@RequestBody phone:String): ResponseEntity<ResponseBody> = service.sms(phone)
}