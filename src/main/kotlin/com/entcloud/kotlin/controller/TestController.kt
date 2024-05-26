package com.entcloud.kotlin.controller

import com.entcloud.kotlin.utils.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("test")
class TestController {

    @GetMapping("1")
    fun test1(value:String): String {
        return Jwt.generateToken(value)
    }

    @GetMapping("2")
    fun test2(token:String): String {
        return Jwt.generateToken(token)
    }
}