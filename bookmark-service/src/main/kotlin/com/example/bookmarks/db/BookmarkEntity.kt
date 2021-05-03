package com.example.bookmarks.db

import org.springframework.data.jpa.domain.AbstractPersistable
import java.util.*
import javax.persistence.CascadeType.ALL
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.ManyToOne
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
data class BookmarkEntity(
    @NotNull var identifier: UUID,
    @NotEmpty var title: String,
    @NotEmpty var description: String,
    @NotNull var reference: String,
    @ManyToOne(optional = false, fetch = EAGER) var user: UserEntity
) : AbstractPersistable<Long>()
