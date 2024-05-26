package com.entcloud.kotlin.repository

import com.entcloud.kotlin.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface UserRepo:JpaRepository<User,Long>, JpaSpecificationExecutor<User>{
    fun queryById(id:Long): User
    fun queryByPhone(phone:String):User
}