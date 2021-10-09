package com.example.bookmarks.api

import com.example.bookmarks.service.User
import com.example.bookmarks.service.UserService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
internal class UserRestController(private val userService: UserService) {

    @GetMapping
    fun currentUser(@AuthenticationPrincipal user: User): User? = userService.findByEmail(user.email)

    @GetMapping
    fun getAllUsers() = userService.findAllUsers()
}