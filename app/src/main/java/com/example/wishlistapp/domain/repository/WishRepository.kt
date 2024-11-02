package com.example.wishlistapp.domain.repository

import com.example.wishlistapp.domain.model.WishItem

interface WishRepository{

    suspend fun upsertWish(wishItem: WishItem)

    suspend fun deleteWish(wishItem: WishItem)

    suspend fun getAllWish(): List<WishItem>

}