package com.iapps.practical.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iapps.practical.model.FeedModel
import java.util.Date

@Entity
data class FeedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "link")
    val link: String,

    @ColumnInfo(name = "media")
    val media: String,

    @ColumnInfo(name = "date_taken")
    val dateTaken: Date,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "published")
    val published: Date,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "author_id")
    val authorId: String,

    @ColumnInfo(name = "tags")
    val tags: String,
)

fun FeedEntity.toFeedModel() = FeedModel(
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