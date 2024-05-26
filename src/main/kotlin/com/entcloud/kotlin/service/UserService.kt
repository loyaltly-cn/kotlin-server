package com.entcloud.kotlin.service

import com.entcloud.kotlin.dto.QueryRequestBody
import com.entcloud.kotlin.http.conflict
import com.entcloud.kotlin.http.serverErr
import com.entcloud.kotlin.model.User
import com.entcloud.kotlin.repository.UserRepo
import com.entcloud.kotlin.utils.createPageable
import com.entcloud.kotlin.utils.createSpecification
import com.entcloud.kotlin.utils.updateModel
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class UserService (val repo: UserRepo){

    fun query(body: QueryRequestBody<User>?): MutableList<User>? = if (body!=null) createPageable(body.page,body.number)?.let { repo.findAll(
        createSpecification<User>(body.param), it).content } else repo.findAll(Sort.by(Sort.Direction.DESC, "id"))

    fun add(user: User): Any? = {
        //手机号的长度限制在11位 长度不对会抛出MysqlDataTruncation (com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column 'phone' at row 1) 且无法捕获
        try {
            repo.save(user)
        } catch (e: DataIntegrityViolationException) {
            conflict("此手机号已被使用")
        } catch (e: Exception) {
            e.printStackTrace()
            serverErr("添加失败")
        }
    }

    fun update(user: User): Any? = updateModel(user, user.id?.let { repo.queryById(it) })?.let { repo.save(it) }

    fun del(user: User) = user.id?.let { repo.deleteById(it) }
}