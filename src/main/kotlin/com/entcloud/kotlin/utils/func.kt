package com.entcloud.kotlin.utils

import jakarta.persistence.criteria.Predicate
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import kotlin.random.Random

fun <T> createSpecification(obj: T?): Specification<T> = Specification { root, _, criteriaBuilder ->
    val predicates = mutableListOf<Predicate>()
    obj!!::class.java.declaredFields.forEach { field ->
        field.isAccessible = true
        val value = field.get(obj)
        if (value != null) predicates.add(criteriaBuilder.equal(root.get<Any>(field.name), value))
    }
    criteriaBuilder.and(*predicates.toTypedArray())
}

fun createPageable(page:Int?,number:Int?): PageRequest? = number?.let { page?.let { it1 -> PageRequest.of(it1, it,Sort.by(Sort.Direction.DESC, "id")) } }

fun <T : Any> updateModel(obj: T?,db:T?): T? {

    obj!!::class.java.declaredFields.forEach { field ->
        field.isAccessible = true
        val value = field.get(obj)
        if (value != null) field.set(db, value)
    }

    return db
}

fun generateRandomNumber(): Int = Random(System.currentTimeMillis()).nextInt(1000, 10000)