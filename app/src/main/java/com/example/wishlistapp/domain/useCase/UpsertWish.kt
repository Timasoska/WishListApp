package com.example.wishlistapp.domain.useCase

import com.example.wishlistapp.domain.model.WishItem
import com.example.wishlistapp.domain.repository.WishRepository
import javax.inject.Inject

class UpsertWish @Inject constructor(
    private val wishRepository: WishRepository
){
    suspend operator fun invoke(wishItem: WishItem){
        wishRepository.upsertWish(wishItem)
    }

}