package com.example.mvvmpj.domain

import com.example.mvvmpj.data.model.Item
import io.reactivex.Completable
import io.reactivex.Single

interface BookRepository {
    fun searchBookList(query : String) : Single<List<Item>>
    fun getAllBookMarkList() : Single<List<Item>>
    fun insertBookMark(item : Item) : Completable
    fun deleteBookMark(isbn : String) : Completable
}