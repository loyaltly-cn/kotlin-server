package com.entcloud.kotlin.service

import com.entcloud.kotlin.dto.ResponseBody
import com.entcloud.kotlin.http.Request
import com.entcloud.kotlin.http.success
import com.entcloud.kotlin.utils.Redis
import com.entcloud.kotlin.utils.generateRandomNumber
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class SmsService(val redis: Redis,val req:Request) {

    fun sms(phone:String): ResponseEntity<ResponseBody> {
        val code = generateRandomNumber()
        redis.pushCode(phone,code)
        req.sms(phone, code.toString())
        return success("验证码已发送")
    }
}