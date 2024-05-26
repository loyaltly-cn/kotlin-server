package com.entcloud.kotlin.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "headers")
class HeadersProperties {
    var key: String? = null
    var value: String? = null
}