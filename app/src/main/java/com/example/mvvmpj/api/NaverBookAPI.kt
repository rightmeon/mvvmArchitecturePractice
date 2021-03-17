package com.example.mvvmpj.api

import com.example.mvvmpj.repository.BookItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverBookAPI {
    @GET("/v1/search/book.json")
    fun getNameBookList(@Query("Query") d_auth : String, @Query("start") start : Int) : Single<BookItem>
}