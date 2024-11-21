package com.example.wishlistapp.presentation.WishListCommon

import com.example.wishlistapp.domain.model.WishItem

sealed class WIshListViewIntent{
    data class UpsertWishList(val wishItem: WishItem) : WIshListViewIntent()
    data class DeleteWishList(val wishItem: WishItem) : WIshListViewIntent()
    //object ShareWishList //Скинуть кому-то свой вишлист?
}