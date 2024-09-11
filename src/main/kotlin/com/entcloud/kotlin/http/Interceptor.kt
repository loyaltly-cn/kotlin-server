package com.entcloud.kotlin.http

import com.entcloud.kotlin.dto.ResponseBody
import com.entcloud.kotlin.properties.HeadersProperties
import com.entcloud.kotlin.properties.SecurityProperties
import com.entcloud.kotlin.utils.Jwt
import com.entcloud.kotlin.utils.Redis
import com.google.gson.Gson
import io.jsonwebtoken.security.SignatureException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.data.redis.RedisSystemException
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

@Component
class Interceptor(
    val securityProperties:SecurityProperties,
    val headers:HeadersProperties,
    val redis:Redis
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val method = request.method
        val uri = request.requestURI
        if (method.equals("OPTIONS", ignoreCase = true) || uri.startsWith("/doc")) return true

        val entCloudHeaderValue = request.getHeader(headers.key)

        response.apply {
            contentType = "application/json;charset=UTF-8"
        }

        if (entCloudHeaderValue === null || !entCloudHeaderValue.equals(headers.value)){
            response.apply {
                status = 418
                writer.print(Gson().toJson(ResponseBody("请联系xxxxx获取API密匙")))
            }

            return false
        }

        if (method.equals("GET", ignoreCase = true)) return true

        if (uri in securityProperties.permitPaths) return true

        val token = request.getHeader("Authorization")

        if (token === null ){
             response.apply {
                status = 400
                writer.print(Gson().toJson(ResponseBody("未携带token")))
            }
            return false
        }

        try {
            val id = Jwt.getUsernameFromToken(token)
            if (redis.get("token:$id") === null) return errAuth(response)
            redis.set("last_online_time:${id}", System.currentTimeMillis())
        }catch (e:RedisSystemException){
            e.printStackTrace()
            response.apply {
                status = 408
                writer.print(Gson().toJson(ResponseBody("请求超时,请稍后重试")))
            }
            return false
        }catch (_:SignatureException){
            return errAuth(response)
        }
        catch (e:Exception){
            e.printStackTrace()
            return errAuth(response)
        }

        return true
    }

    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        // 在处理器方法调用之后执行逻辑，渲染视图之前
    }

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        response.setHeader("Access-Control-Allow-Origin", "*")
        response.setHeader("Content-Type", "application/json;charset=UTF-8")
    }
}


fun errAuth(response: HttpServletResponse): Boolean {
    response.apply {
        status = 401
        writer.print(Gson().toJson(ResponseBody("登录状态已失效,请重新登录")))
    }
    return false
}


