package com.example.bookmarks.api

import com.example.bookmarks.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
internal class UserRestController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers() = userService.findAllUsers()
}