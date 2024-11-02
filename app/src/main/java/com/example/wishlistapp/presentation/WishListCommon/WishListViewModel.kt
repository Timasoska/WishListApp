package com.example.wishlistapp.presentation.WishListCommon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapp.domain.model.WishItem
import com.example.wishlistapp.domain.useCase.DeleteWish
import com.example.wishlistapp.domain.useCase.GetAllWish
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val getAllWish: GetAllWish,
    private val deleteWish: DeleteWish
) : ViewModel() {

    private val _wishListState = MutableStateFlow<List<WishItem>>(emptyList())
    val wishListState = _wishListState.asStateFlow()

    private val _orderByTitleState = MutableStateFlow(false)
    val orderByTitleState = _orderByTitleState.asStateFlow()

    fun loadWish(){
        viewModelScope.launch {
            _wishListState.update {
                getAllWish.invoke(orderByTitleState.value)
            }
        }
    }

    fun deleteWish(wishItem: WishItem){
        viewModelScope.launch {
            deleteWish.invoke(wishItem)
            loadWish()
        }
    }

    fun changeOrder(){
        _orderByTitleState.update { !it }
        loadWish()
    }


}