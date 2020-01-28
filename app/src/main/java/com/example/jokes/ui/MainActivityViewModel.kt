package com.example.jokes.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jokes.api.JokeRepository
import com.example.jokes.database.FavoriteRepository
import com.example.jokes.model.Favorite
import com.example.jokes.model.Joke
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val jokeRepository = JokeRepository()
    val joke = MutableLiveData<Joke>()
    val error = MutableLiveData<String>()

    private val favoriteRepository = FavoriteRepository(application.applicationContext)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    val favorite: LiveData<List<Favorite>> = favoriteRepository.getFavorite()

    fun getJokes() {
        jokeRepository.getRandomNormalJoke().enqueue(object : Callback<Joke> {
            override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                if (response.isSuccessful) {
                    joke.value = response.body()
                } else error.value = "An error occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<Joke>, t: Throwable) {
                error.value = t.message
            }
        })
    }

    fun insertFavorite(favorite: Favorite) {
        ioScope.launch {
            favoriteRepository.insertFavorite(favorite)
        }
    }

    fun deleteFavorite(favorite: Favorite) {
        ioScope.launch {
            favoriteRepository.deleteFavorite(favorite)
        }
    }

    fun deleteAllFavorite() {
        ioScope.launch {
            favoriteRepository.deleteAllFavorite()
        }
    }
}