package com.entcloud.kotlin.service

import com.entcloud.kotlin.dto.LoginCodeBody
import com.entcloud.kotlin.dto.LoginPasswordBody
import com.entcloud.kotlin.dto.ResponseBody
import com.entcloud.kotlin.dto.SuccessLoginBody
import com.entcloud.kotlin.http.gone
import com.entcloud.kotlin.http.noAcceptable
import com.entcloud.kotlin.http.success
import com.entcloud.kotlin.model.User
import com.entcloud.kotlin.repository.UserRepo
import com.entcloud.kotlin.utils.Jwt
import com.entcloud.kotlin.utils.Redis
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class AuthService (
    val redis: Redis,
    val userRepo:UserRepo,
){
    fun code(body:LoginCodeBody): ResponseEntity<ResponseBody> {

        try {
            if (body.code != redis.get(body.phone)) return gone("抱歉，验证码验证失败，请重新尝试。")
           return loginCode(body)
        }catch (_:Exception){
            if (body.auto == true){
                userRepo.save(User(phone = body.phone))
                return loginCode(body)
            }
        }
        return gone("用户不存在")
    }


    fun password(body:LoginPasswordBody): ResponseEntity<ResponseBody> =
        try {
            val user = userRepo.queryByPhone(body.phone)
            if (body.password != user.password) noAcceptable("手机号或密码错误") else login(user)
        }catch (_:Exception){
            gone("用户不存在")
        }


    fun logout(request: HttpServletRequest) = redis.clear("token:"+Jwt.getUsernameFromToken(request.getHeader("Authorization")))

    fun loginCode(body:LoginCodeBody) = login(userRepo.queryByPhone(body.phone))

    fun login(user:User): ResponseEntity<ResponseBody> {
        val token = Jwt.generateToken(user.id.toString())
        redis.pushToken("token:"+user.id.toString(),token)
        return success("登录成功",SuccessLoginBody(user,token))
    }


    fun baseInfo(request: HttpServletRequest): User {
        val user = userRepo.queryById(Jwt.getUsernameFromToken(request.getHeader("Authorization")).toLong())
        return user
    }
}