package com.entcloud.kotlin.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.util.*

object Jwt {

    private val SECRET_KEY = Keys.hmacShaKeyFor("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5IiwiZXhwIjoxNzE3NDQ1ODcwfQ._jhMcoCwC_zHt6IIXNeq5dRT9LqQoMMNn3WQLRhyWnRe5IZ5-FB0xym0TKU5Rut2jPMR4R8DGS24_LWuP5YWMQ".toByteArray())
    private const val EXPIRATION_TIME: Long = 2592000000

    fun generateToken(id: String): String = Jwts.builder().setSubject(id).setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SECRET_KEY).compact()

    fun getUsernameFromToken(token: String): String = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).body.subject
}