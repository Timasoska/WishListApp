package com.example.wishlistapp.data.repository

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
        wishDao.upsertWishEntity(wishItem.toWishEntityForInsert())
    }

    override suspend fun deleteWish(wishItem: WishItem) {
        wishDao.deleteWishEntity(wishItem.toWishEntityForDelete())
    }

    override suspend fun getAllWish(): List<WishItem> {
        return wishDao.getAllWishEntities().map { it.toWishItem() }
    }

}