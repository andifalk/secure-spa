package com.example.bookmarks.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
internal class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http {
            cors { }
            csrf { disable() }
            httpBasic { }
            formLogin { }
            authorizeRequests {
                authorize(anyRequest, authenticated)
            }
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun securityEvaluationContextExtension(): SecurityEvaluationContextExtension {
        return SecurityEvaluationContextExtension()
    }
}