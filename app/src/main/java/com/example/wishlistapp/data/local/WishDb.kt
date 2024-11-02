package com.example.wishlistapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WishEntity::class],
    version = 1
)
abstract class WishDb:RoomDatabase(){
    abstract val wishDao: WishDao
}