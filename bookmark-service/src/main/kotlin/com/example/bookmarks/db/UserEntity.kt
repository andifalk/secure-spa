package com.example.bookmarks.db

import org.springframework.data.jpa.domain.AbstractPersistable
import java.util.*
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
data class UserEntity(
    @NotNull var identifier: UUID,
    @NotNull @Email var email: String,
    @NotEmpty var firstName: String,
    @NotEmpty var lastName: String,
    @NotEmpty @Size(max = 255) var password: String,
    @NotEmpty @ElementCollection(fetch = EAGER) var roles: List<String>
) : AbstractPersistable<Long>()
