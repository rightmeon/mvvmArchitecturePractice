package com.example.mvvmpj.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface BookMarkDao {
    @Insert
    fun insertBookData(bookItem: Item) : Completable

    @Query("select * from BookMarkData")
    fun allBookMarkData() : Single<List<Item>>

    @Query("delete from BookMarkData where isbn = :isbn")
    fun deleteBookData(isbn : String) : Completable
}