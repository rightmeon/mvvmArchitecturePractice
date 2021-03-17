package com.example.mvvmpj.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmpj.data.model.Item
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