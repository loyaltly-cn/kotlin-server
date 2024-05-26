package com.entcloud.kotlin.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "security")
class SecurityProperties {
    var permitPaths: List<String> = mutableListOf()
}