package com.example.wishlistapp.domain.useCase

import com.example.wishlistapp.domain.model.WishItem
import com.example.wishlistapp.domain.repository.WishRepository

class DeleteWish(
    private val wishRepository: WishRepository
) {

    suspend operator fun invoke(wishItem: WishItem){
        wishRepository.deleteWish(wishItem)
    }

}