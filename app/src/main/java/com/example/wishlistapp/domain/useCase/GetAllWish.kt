package com.example.wishlistapp.domain.useCase

import com.example.wishlistapp.domain.model.WishItem
import com.example.wishlistapp.domain.repository.WishRepository

class GetAllWish(
    private val wishRepository: WishRepository
) {

    suspend operator fun invoke(
        isOrderByTitle: Boolean
    ): List<WishItem> {
        return if (isOrderByTitle){
            wishRepository.getAllWish().sortedBy { it.title.lowercase() }
        } else {
            wishRepository.getAllWish().sortedBy { it.dateAdded }
        }
    }


}