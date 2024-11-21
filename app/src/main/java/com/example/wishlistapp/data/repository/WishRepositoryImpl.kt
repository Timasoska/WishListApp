package com.example.wishlistapp.data.repository

import android.util.Log
import com.example.wishlistapp.data.local.WishDb
import com.example.wishlistapp.data.mapper.toWishEntityForDelete
import com.example.wishlistapp.data.mapper.toWishEntityForInsert
import com.example.wishlistapp.data.mapper.toWishItem
import com.example.wishlistapp.domain.model.WishItem
import com.example.wishlistapp.domain.repository.WishRepository

class WishRepositoryImpl(
    wishDb: WishDb
) : WishRepository {

    private val wishDao = wishDb.wishDao

    override suspend fun upsertWish(wishItem: WishItem) {
        Log.d("WishRepositoryImpl", "Upserting wish: $wishItem")
        wishDao.upsertWishEntity(wishItem.toWishEntityForInsert())
    }

    override suspend fun deleteWish(wishItem: WishItem) {
        Log.d("WishRepositoryImpl", "Deleting wish: $wishItem")
        wishDao.deleteWishEntity(wishItem.toWishEntityForDelete())
    }

    override suspend fun getAllWish(): List<WishItem> {
        Log.d("WishRepositoryImpl", "Fetching all wishes")
        return wishDao.getAllWishEntities().map { it.toWishItem() }
    }

}