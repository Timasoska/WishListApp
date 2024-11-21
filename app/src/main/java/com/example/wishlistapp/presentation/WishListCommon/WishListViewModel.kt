package com.example.wishlistapp.presentation.WishListCommon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapp.domain.model.WishItem
import com.example.wishlistapp.domain.useCase.DeleteWish
import com.example.wishlistapp.domain.useCase.GetAllWish
import com.example.wishlistapp.domain.useCase.UpsertWish
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val getAllWish: GetAllWish,
    private val deleteWish: DeleteWish,
    private val upsertWish: UpsertWish
) : ViewModel() {

    private val _viewState = MutableStateFlow<WishListViewState>(WishListViewState.Loading)
    val viewState = _viewState.asStateFlow()

    private val _orderByTitle = MutableStateFlow(false)
    val orderByTitleState = _orderByTitle.asStateFlow()

    init {
        loadWish()
    }

    fun handleIntent(intent: WIshListViewIntent){
        when(intent){
            is WIshListViewIntent.DeleteWishList -> {
                viewModelScope.launch {
                    Log.d("WishListViewModel", "Delete wish: ${intent.wishItem}")
                    deleteWish.invoke(intent.wishItem)
                    loadWish()
                }
            }
            is WIshListViewIntent.UpsertWishList -> {
                viewModelScope.launch {
                    Log.d("WishListViewModel", "Upserting wish: ${intent.wishItem}")
                    upsertWish.invoke(intent.wishItem)
                    loadWish()
                }
            }
        }

    }

    fun loadWish(){
        viewModelScope.launch {
            _viewState.value = WishListViewState.Loading
            try{
                Log.d("WishListViewModel", "Loading wishes, ordered by title: ${orderByTitleState.value}")
                val wishes = getAllWish.invoke(_orderByTitle.value)
                Log.d("WishListViewModel", "Loaded wishes: $wishes")
                _viewState.value = WishListViewState.Success(wishes)
            } catch (e: Exception){
                _viewState.value = WishListViewState.Error(e.message ?: ("Unknown error in loadWish"))
            }
        }
    }

    fun changeOrder(){
        _orderByTitle.update { !it }
        loadWish()
    }

}