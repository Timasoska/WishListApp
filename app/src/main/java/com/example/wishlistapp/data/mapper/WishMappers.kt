package com.example.wishlistapp.data.mapper

import com.example.wishlistapp.data.local.WishEntity
import com.example.wishlistapp.domain.model.WishItem

fun WishItem.toWishEntityForInsert(
): WishEntity{
    return WishEntity(
        title = title,
        description = description,
        imageUrl = imageUrl,
        dateAdded = dateAdded
    )
}

fun WishItem.toWishEntityForDelete(
): WishEntity {
    return WishEntity(
        title = title,
        description = description,
        imageUrl = imageUrl,
        dateAdded = dateAdded,
        id = id
    )
}

fun WishEntity.toWishItem(): WishItem{
    return WishItem(
        title = title,
        description = description,
        imageUrl = imageUrl,
        dateAdded = dateAdded,
        id = id ?: 0
    )
}