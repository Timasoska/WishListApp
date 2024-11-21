package com.example.wishlistapp.presentation.WishListCommon

import com.example.wishlistapp.domain.model.WishItem

sealed class WishListViewState {
    data class Success(val data : List<WishItem>) : WishListViewState()
    object Loading : WishListViewState()
    data class Error(val message: String) : WishListViewState()
}