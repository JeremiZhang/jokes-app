package com.example.jokes.api

import com.example.jokes.model.Joke
import retrofit2.Call
import retrofit2.http.GET

interface JokeApiService {
    // The GET method needed to retrieve a random number trivia.
    @GET("/jokes/random")
    fun getRandomNormalJoke(): Call<Joke>

    @GET("/jokes/programming/random")
    fun getRandomProgrammingJoke(): Call<Joke>
}