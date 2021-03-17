package com.example.mvvmpj.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.example.mvvmpj.api.NaverBookAPI
import com.example.mvvmpj.repository.Item
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class BookViewModel @Inject constructor(var api : NaverBookAPI) : BaseViewModel() {
    private var liveData = MutableLiveData<List<Item>>()

    fun getNameBook(query : String) {
        compositeDisposable.add(api.getNameBookList(query, 1)
            .map { it.Items }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({liveData.value = it}, {it.printStackTrace()}))
    }

    fun getData() : LiveData<List<Item>> = liveData

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}