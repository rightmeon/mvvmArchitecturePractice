package com.example.mvvmpj.repository

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "BookMarkData")
data class Item(
    @SerializedName("title")
    var title : String,
    @SerializedName("link")
    var link : String,
    @SerializedName("image")
    var image : String,
    @SerializedName("author")
    var author : String,
    @SerializedName("price")
    var price : String,
    @SerializedName("discount")
    var discount : String,
    @SerializedName("publisher")
    var publisher : String,
    @PrimaryKey
    @SerializedName("isbn")
    var isbn : String,
    @SerializedName("description")
    var description : String
) : Serializable

