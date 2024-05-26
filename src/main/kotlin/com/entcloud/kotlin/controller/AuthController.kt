package com.entcloud.kotlin.controller

import com.entcloud.kotlin.dto.LoginCodeBody
import com.entcloud.kotlin.dto.LoginPasswordBody
import com.entcloud.kotlin.service.AuthService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
@Tag(name = "鉴权API")
class AuthController (val service:AuthService){

    @Operation(description = "验证码登录")
    @PostMapping("login/code")
    fun code(@RequestBody body:LoginCodeBody) = service.code(body)

    @Operation(description = "手机号密码登录")
    @PostMapping("login/password")
    fun password(@RequestBody body:LoginPasswordBody) = service.password(body)

    @Operation(description = "注销 只需要header里带上token就行")
    @PostMapping("logout")
    fun logout(request: HttpServletRequest) = service.logout(request)

    @Operation(description = "小程序端获取用户及关联的基本信息")
    @PostMapping("mp")
    fun baseInfo(request: HttpServletRequest) = service.baseInfo(request)
}