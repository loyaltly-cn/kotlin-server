package com.entcloud.kotlin.controller

import com.entcloud.kotlin.dto.QueryRequestBody
import com.entcloud.kotlin.model.User
import com.entcloud.kotlin.service.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
@Tag(name = "用户API")
class UserController(val service: UserService) {

    @PostMapping("query")
    fun query(@RequestBody body: QueryRequestBody<User>?): MutableIterable<User>? = service.query(body)

    @PostMapping
    fun add(@RequestBody user: User): Any? = service.add(user)

    @PutMapping
    fun update(@RequestBody user: User) = service.update(user)

    @DeleteMapping
    fun del(@RequestBody user: User) = service.del(user)

}


