package com.example.bookmarks.service

import com.example.bookmarks.db.BookmarkEntity
import com.example.bookmarks.db.BookmarkEntityRepository
import com.example.bookmarks.db.UserEntityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
internal class BookmarkService(
    private val bookmarkEntityRepository: BookmarkEntityRepository,
    private val userEntityRepository: UserEntityRepository
) {

    @Transactional
    fun createBookmark(bookmark: Bookmark) {
        userEntityRepository.findOneByIdentifier(bookmark.userId)?.apply {
            bookmarkEntityRepository.save(bookmark.let {
                BookmarkEntity(
                    it.identifier,
                    it.title,
                    it.description,
                    it.reference,
                    this
                )
            })
        } ?: throw IllegalArgumentException("No user found for ${bookmark.userId}")
    }

    fun findAllBookmarks() = bookmarkEntityRepository.findAllBookmarks().map {
        Bookmark(it.identifier, it.title, it.description, it.reference, it.user.identifier)
    }

    fun findBookmarkByIdentifier(bookmarkIdentifier: UUID) = bookmarkEntityRepository.findBookmarkByIdentifier(bookmarkIdentifier)?.let {
        Bookmark(it.identifier, it.title, it.description, it.reference, it.user.identifier)
    }

    @Transactional
    fun deleteBookmark(bookmarkIdentifier: UUID) {
        bookmarkEntityRepository.deleteBookmarkByIdentifier(bookmarkIdentifier)
    }

}