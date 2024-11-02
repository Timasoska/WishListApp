package com.example.wishlistapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface WishDao {

    @Upsert
    suspend fun upsertWishEntity(wishEntity: WishEntity)

    @Query("SELECT * FROM wishEntity")
    suspend fun getAllWishEntities(): List<WishEntity>

    @Delete
    suspend fun deleteWishEntity(wishEntity: WishEntity)

}