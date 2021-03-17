package com.example.mvvmpj.domain

import com.example.mvvmpj.data.local.BookMarkDao
import com.example.mvvmpj.data.model.Item
import com.example.mvvmpj.data.remote.NaverBookAPI
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api : NaverBookAPI,
    private val dao : BookMarkDao) : BookRepository {

    override fun searchBookList(query: String): Single<List<Item>> {
        return api.getNameBookList(query, 1)
            .map { it.Items }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAllBookMarkList(): Single<List<Item>> {
        return dao.allBookMarkData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertBookMark(item: Item): Completable {
        return dao.insertBookData(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteBookMark(isbn: String): Completable {
        return dao.deleteBookData(isbn)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}