package com.entcloud.kotlin.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApi {

    @Bean
    fun customOpenAPI(): OpenAPI = OpenAPI().addServersItem( Server().url("xxxxxxxx")).info(Info().title("xxxxxx"))

    @Bean
    fun publicApi(): GroupedOpenApi = GroupedOpenApi.builder().group("public").pathsToMatch("/**").build()
}