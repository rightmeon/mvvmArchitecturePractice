package com.example.mvvmpj.data.model

import com.google.gson.annotations.SerializedName

data class BookItem(
    @SerializedName("lastBuildDate")
    var lastBuildDate : String,
    @SerializedName("total")
    var total : Int,
    @SerializedName("start")
    var start : Int,
    @SerializedName("display")
    var display : Int,
    @SerializedName("items")
    var Items : List<Item>
)
