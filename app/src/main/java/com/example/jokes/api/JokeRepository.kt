package com.example.jokes.api

class JokeRepository {
    private val jokeApi: JokeApiService = JokeApi.createApi()

    fun getRandomNormalJoke() = jokeApi.getRandomNormalJoke()
    fun getRandomProgrammingJoke() = jokeApi.getRandomProgrammingJoke()

}
