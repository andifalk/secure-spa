package com.example.bookmarks.service

import java.util.*

data class Bookmark(
    var identifier: UUID,
    var title: String,
    var description: String,
    var reference: String,
    var userId: UUID
)
