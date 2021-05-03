package com.example.bookmarks.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.IdGenerator
import org.springframework.util.JdkIdGenerator

@Configuration
internal class IdGeneratorConfiguration {

    @Bean
    fun idGenerator(): IdGenerator = JdkIdGenerator()

}