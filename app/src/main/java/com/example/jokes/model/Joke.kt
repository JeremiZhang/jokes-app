package com.example.jokes.model

import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("id") var id: Int,
    @SerializedName("type") var type: String,
    @SerializedName("setup") var setup: String,
    @SerializedName("punchline") var punchline: String
)