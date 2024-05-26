package com.entcloud.kotlin.config

import com.entcloud.kotlin.properties.HeadersProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class Cors (val headerProperties:HeadersProperties) :WebMvcConfigurer{
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS")
            .allowedHeaders("Content-Type","Authorization",headerProperties.key)
    }
}