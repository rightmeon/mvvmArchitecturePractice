package com.example.mvvmpj.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmpj.data.model.Item

@Database(entities = [Item::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookMarkDao() : BookMarkDao
}