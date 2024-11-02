package com.example.wishlistapp.presentation.WishListCommon

sealed class WishListViewState {
    data class Success(val data : String) : WishListViewState()
    object Loading : WishListViewState()
    data class Error(val message: String) : WishListViewState()
}