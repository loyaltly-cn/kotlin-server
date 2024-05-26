package com.entcloud.kotlin.utils

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class Redis(val redis: RedisTemplate<String, Any>) {

    fun get(key: String): Any? = redis.opsForValue().get(key)

    fun set(key: String, value: Any) = redis.opsForValue().set(key, value)

    fun pushCode(phone: String, code: Int) = redis.opsForValue().set(phone,code,5,TimeUnit.MINUTES)

    fun pushToken(token: String,value:Any) = redis.opsForValue().set(token,value,30,TimeUnit.DAYS)

    fun clear(key:String) = redis.delete(key)
}