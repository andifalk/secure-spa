package com.example.bookmarks.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

internal interface BookmarkEntityRepository : JpaRepository<BookmarkEntity, Long> {

    @Query("SELECT bm FROM BookmarkEntity bm WHERE bm.user.email = ?#{ principal?.username }")
    fun findAllBookmarks(): List<BookmarkEntity>

    @Query("SELECT bm FROM BookmarkEntity bm WHERE bm.identifier = :bookmarkIdentifier AND bm.user.email = ?#{ principal?.username }")
    fun findBookmarkByIdentifier(@Param("bookmarkIdentifier") bookmarkIdentifier: UUID): BookmarkEntity?

    @Modifying
    @Query("DELETE FROM BookmarkEntity bm WHERE bm.identifier = :bookmarkIdentifier")
    fun deleteBookmarkByIdentifier(@Param("bookmarkIdentifier") bookmarkIdentifier: UUID)
}