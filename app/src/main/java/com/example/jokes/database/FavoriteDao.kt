package com.example.jokes.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jokes.model.Favorite


@Dao
interface FavoriteDao {

    @Insert
    suspend fun insertFavorite(favorite: Favorite)

    @Update
    suspend fun updateFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    @Query("SELECT * FROM Favorite")
    fun getFavorite(): LiveData<List<Favorite>>

    @Query("DELETE FROM Favorite")
    fun deleteAllFavorite()

}
