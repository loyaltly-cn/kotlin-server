package com.entcloud.kotlin.http

import com.entcloud.kotlin.dto.LoginCodeBody
import com.entcloud.kotlin.properties.GainProperties
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForObject

@Component
class Request(val req: RestTemplate, val gain:GainProperties) {

    fun sms(phone:String,code:String) =
        try {
        req.postForObject(gain.sms+"sms",HttpEntity(LoginCodeBody(phone, code)))
    }catch (_:Exception){}


}
