package com.example.bookmarks.security

import com.example.bookmarks.service.User
import com.example.bookmarks.service.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
internal class BookmarkUserDetailsService(private val userService: UserService) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return userService.findByEmail(username)?.let {
            User(it.identifier, it.email, it.firstName, it.lastName, it.password, it.roles)
        } ?: throw UsernameNotFoundException("No user found for $username")
    }
}