package com.example.bookmarks.api

import com.example.bookmarks.service.Bookmark
import com.example.bookmarks.service.BookmarkService
import com.example.bookmarks.service.User
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.util.IdGenerator
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.constraints.NotEmpty

@RestController
@RequestMapping("/api/bookmarks")
@Validated
internal class BookmarkRestController(
    private val bookmarkService: BookmarkService,
    private val idGenerator: IdGenerator
) {

    @ResponseStatus(CREATED)
    @PostMapping
    fun createBookmark(@RequestBody bookmarkRequest: BookmarkRequest, @AuthenticationPrincipal user: User) {
        bookmarkService.createBookmark(bookmarkRequest.let {
            Bookmark(
                idGenerator.generateId(),
                it.title,
                it.description,
                it.reference,
                user.identifier
            )
        })
    }

    @GetMapping
    fun getBookmarks() = bookmarkService.findAllBookmarks()

    @GetMapping("/{bookmarkIdentifier}")
    fun getBookmark(@PathVariable bookmarkIdentifier: UUID): ResponseEntity<Bookmark> =
        bookmarkService.findBookmarkByIdentifier(bookmarkIdentifier)?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{bookmarkIdentifier}")
    fun deleteBookmark(@PathVariable bookmarkIdentifier: UUID) = bookmarkService.deleteBookmark(bookmarkIdentifier)
}

internal data class BookmarkRequest(
    @NotEmpty var title: String,
    @NotEmpty var description: String,
    @NotEmpty var reference: String
)