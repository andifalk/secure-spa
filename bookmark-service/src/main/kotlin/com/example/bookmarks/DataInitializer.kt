package com.example.bookmarks

import com.example.bookmarks.db.BookmarkEntity
import com.example.bookmarks.db.BookmarkEntityRepository
import com.example.bookmarks.db.UserEntity
import com.example.bookmarks.db.UserEntityRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

const val BOOKMARK_1 = "58d7dfdf-6dc9-4c96-976d-7b0a8e62b694"
const val BOOKMARK_2 = "c518525-cd32-4801-a459-06fa43b988b7"
const val USER_1 = "3342641-90d2-422c-a78b-5d0c2d907a63"
const val USER_2 = "928d5ec3-eab1-494c-9fc2-9bf3b3012cfa"

@Component
internal class DataInitializer(
    private val userEntityRepository: UserEntityRepository,
    private val bookmarkEntityRepository: BookmarkEntityRepository,
    private val passwordEncoder: PasswordEncoder
) : CommandLineRunner {

    @Transactional
    override fun run(vararg args: String?) {

        val logger = LoggerFactory.getLogger(DataInitializer::class.java)

        logger.info("Creating users")

        val user = userEntityRepository.save(
            UserEntity(
                UUID.fromString(USER_1),
                "user@example.com", "Max", "Mustermann", passwordEncoder.encode("secret"), listOf("USER")
            )
        )
        userEntityRepository.save(
            UserEntity(
                UUID.fromString(USER_2),
                "admin@example.com", "Hans", "Admin", passwordEncoder.encode("admin"), listOf("USER", "ADMIN")
            )
        )

        logger.info("Creating bookmarks")

        bookmarkEntityRepository.save(
            BookmarkEntity(
                UUID.fromString(BOOKMARK_1), "Spring Blog",
                "<b>The Spring Blog</b>", "https://spring.io/blog", user
            )
        )
        bookmarkEntityRepository.save(
            BookmarkEntity(
                UUID.fromString(BOOKMARK_2), "haveibeenpwned.com",
                "Have <b>I</b> been pwned!", "https://haveibeenpwned.com", user
            )
        )
    }
}