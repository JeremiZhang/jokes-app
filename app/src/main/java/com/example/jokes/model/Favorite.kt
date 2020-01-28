package com.example.jokes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity//(tableName = "Favorite")
data class Favorite(

    //@ColumnInfo(name = "title")
    var setup: String,

    //@ColumnInfo(name = "platform")
    var answer: String,

    @PrimaryKey
    var id: Long? = null
) : Parcelable