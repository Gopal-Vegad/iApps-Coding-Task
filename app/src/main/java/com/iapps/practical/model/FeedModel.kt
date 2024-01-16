package com.iapps.practical.model

import com.iapps.practical.data.local.entity.FeedEntity
import java.util.Date

data class FeedModel(
    val id: Long,
    val title: String,
    val link: String,
    val media: String,
    val dateTaken: Date?,
    val description: String,
    val published: Date?,
    val author: String,
    val authorId: String,
    val tags: String,
)

fun FeedModel.toFeedEntity() = FeedEntity(
    id = id,
    title = title,
    link = link,
    media = media,
    dateTaken = dateTaken,
    description = description,
    published = published,
    author = author,
    authorId = authorId,
    tags = tags
)