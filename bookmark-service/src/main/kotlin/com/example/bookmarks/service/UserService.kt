package com.example.bookmarks.service

import com.example.bookmarks.db.UserEntityRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.IdGenerator

@Service
@Transactional(readOnly = true)
internal class UserService(
    private val userEntityRepository: UserEntityRepository,
    private val idGenerator: IdGenerator
) {

    fun findByEmail(email: String) = userEntityRepository.findOneByEmail(email)?.let {
        User(it.identifier, it.email, it.firstName, it.lastName, it.password, it.roles)
    }

    @PreAuthorize("hasRole('ADMIN')")
    fun findAllUsers() = userEntityRepository.findAll()
        .map { User(it.identifier, it.email, it.firstName, it.lastName, it.password, it.roles) }

}