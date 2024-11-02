package com.example.wishlistapp.domain.model


data class WishItem (
    var title: String,
    var description: String,
    var imageUrl: String,

    var dateAdded: Long,

    val id: Int? = 0

)