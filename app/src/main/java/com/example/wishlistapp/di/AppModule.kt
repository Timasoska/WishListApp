package com.example.wishlistapp.di

import android.app.Application
import androidx.room.Delete
import androidx.room.Room
import com.example.wishlistapp.data.local.WishDb
import com.example.wishlistapp.data.repository.WishRepositoryImpl
import com.example.wishlistapp.domain.repository.WishRepository
import com.example.wishlistapp.domain.useCase.DeleteWish
import com.example.wishlistapp.domain.useCase.GetAllWish
import com.example.wishlistapp.domain.useCase.UpsertWish
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun providesWishDb(application: Application): WishDb{
        return Room.databaseBuilder(
            application,
            WishDb::class.java,
            "wish_db.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesWishRepository(
        wishDb: WishDb
    ): WishRepository {
        return WishRepositoryImpl(wishDb)
    }


    @Provides
    @Singleton
    fun providesGetAllWish(
        wishRepository: WishRepository
    ): GetAllWish {
        return GetAllWish(wishRepository)
    }

    @Provides
    @Singleton
    fun providesDeleteWish(
        wishRepository: WishRepository
    ): DeleteWish {
        return DeleteWish(wishRepository)
    }

    @Provides
    @Singleton
    fun providesUpsertWish(
        wishRepository: WishRepository
    ): UpsertWish {
        return UpsertWish(wishRepository)
    }


}