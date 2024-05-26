package com.entcloud.kotlin.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "gain")
class GainProperties {
    var sms:String? = null
    var oss:String? = null
}