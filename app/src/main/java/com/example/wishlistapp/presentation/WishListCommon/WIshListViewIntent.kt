package com.example.wishlistapp.presentation.WishListCommon

sealed class WIshListViewIntent {
    object AddWishList : WIshListViewIntent()
    object DeleteWishList : WIshListViewIntent()
    object EditWishList : WIshListViewIntent()
    //object ShareWishList //Скинуть кому-то свой вишлист?
}