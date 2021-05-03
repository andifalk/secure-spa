package com.example.bookmarks.db

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

internal interface UserEntityRepository : JpaRepository<UserEntity, Long> {

    fun findOneByIdentifier(identifier: UUID): UserEntity?

    fun findOneByEmail(email: String): UserEntity?

}