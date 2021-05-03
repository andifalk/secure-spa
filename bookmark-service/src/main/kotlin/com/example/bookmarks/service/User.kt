package com.example.bookmarks.service

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class User(
    var identifier: UUID,
    var email: String,
    var firstName: String,
    var lastName: String,
    private var password: String,
    var roles: List<String>
) : UserDetails {
    @JsonIgnore
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles.map { SimpleGrantedAuthority("ROLE_$it") }.toMutableList()
    }

    @JsonIgnore
    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String = email

    @JsonIgnore
    override fun isAccountNonExpired() = true

    @JsonIgnore
    override fun isAccountNonLocked() = true

    @JsonIgnore
    override fun isCredentialsNonExpired() = true

    @JsonIgnore
    override fun isEnabled() = true
}
