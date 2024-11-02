package com.example.wishlistapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WishEntity (
    var title: String,
    var description: String,
    var imageUrl: String,

    var dateAdded: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0


)