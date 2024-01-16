package com.iapps.practical.data.remote.feed.response

import com.google.gson.annotations.SerializedName

data class FeedResponse(
	@SerializedName("link")
	val link: String? = null,

	@SerializedName("description")
	val description: String? = null,

	@SerializedName("modified")
	val modified: String? = null,

	@SerializedName("generator")
	val generator: String? = null,

	@SerializedName("title")
	val title: String? = null,

	@SerializedName("items")
	val items: List<ItemsItem?>? = null
)

data class ItemsItem(
	@SerializedName("author")
	val author: String? = null,

	@SerializedName("link")
	val link: String? = null,

	@SerializedName("description")
	val description: String? = null,

	@SerializedName("media")
	val media: Media? = null,

	@SerializedName("published")
	val published: String? = null,

	@SerializedName("title")
	val title: String? = null,

	@SerializedName("author_id")
	val authorId: String? = null,

	@SerializedName("date_taken")
	val dateTaken: String? = null,

	@SerializedName("tags")
	val tags: String? = null
)

data class Media(
	@SerializedName("m")
	val m: String? = null
)
