package com.entcloud.kotlin.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
@Schema(name = "用户")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(insertable = false, columnDefinition = "VARCHAR(255) DEFAULT '用户_ent_910a48d21c91b9'")
    var name:String? = null,
    @Column(insertable = false,columnDefinition = "VARCHAR(255) DEFAULT 'https://pailemapro.oss-cn-shanghai.aliyuncs.com/public/default-avatar.png'")
    var avatar:String? = null,
    @Column(insertable = false,columnDefinition = "BIT DEFAULT false")
    var sex:Boolean? = null,
    var password:String? = null,
    @Column(unique = true, nullable = false, length = 11)
    var phone:String? = null,
    @Column(insertable = false,columnDefinition = "BIT DEFAULT true")
    var state:Boolean? = null,
    @Schema(description = "身份决定小程序和web后台展示的页面及操作权限 详情请查看")
    @Column(insertable = false,columnDefinition = "TINYINT DEFAULT 0")
    var role:Role? = null,
    @Column(insertable = false, updatable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    val createTime:java.sql.Timestamp? = null,
    val openid:String? = null
)

enum class Role{
    USER,
    FREELANCE_PHOTOGRAPHER,
    STAFF_PHOTOGRAPHER,
    PHOTOGRAPHY_AGENCY,
    FACTORY,
    DISTRIBUTOR,
    FINANCE,
    ADMIN
}