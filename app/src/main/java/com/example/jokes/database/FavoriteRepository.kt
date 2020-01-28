package com.example.jokes.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.jokes.model.Favorite

class FavoriteRepository(context: Context) {

    private val favoriteDao: FavoriteDao

    init {
        val favoriteDaoRoomDatabase =
            FavoriteRoomDatabase.getDatabase(context)
        favoriteDao = favoriteDaoRoomDatabase!!.favoriteDao()
    }

    suspend fun insertFavorite(favorite: Favorite) {
        favoriteDao.insertFavorite(favorite)
    }

    suspend fun updateFavorite(favorite: Favorite) {
        favoriteDao.updateFavorite(favorite)
    }

    suspend fun deleteFavorite(favorite: Favorite) {
        favoriteDao.deleteFavorite(favorite)
    }

    fun getFavorite(): LiveData<List<Favorite>> {
        return favoriteDao.getFavorite()
    }

    fun deleteAllFavorite() {
        favoriteDao.deleteAllFavorite()
    }

}
