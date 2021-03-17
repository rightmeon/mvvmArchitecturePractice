package com.example.mvvmpj.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmpj.repository.BookMarkDao
import com.example.mvvmpj.repository.EventEnum
import com.example.mvvmpj.repository.Item
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BookMarkViewModel @Inject constructor(private val dao : BookMarkDao) : BaseViewModel() {
    private var bookMarkLiveData = MutableLiveData<List<Item>>()
    private val eventLiveData = MutableLiveData<EventEnum>()

    fun getBookMarkList(){
        compositeDisposable.add(dao.allBookMarkData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Item>>() {
                override fun onSuccess(t: List<Item>) {
                    bookMarkLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            }))
    }

    fun insertBookMark(item : Item){
        compositeDisposable.add(
            dao.insertBookData(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({eventLiveData.value = EventEnum.AddBookMark}, {eventLiveData.value = EventEnum.ErrorAddBookMark}))
    }

    fun deleteBookMark(isbn : String){
        compositeDisposable.add(
            dao.deleteBookData(isbn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({eventLiveData.value = EventEnum.DeleteBookMark}, {eventLiveData.value = EventEnum.ErrorDeleteBookMark}))
    }

    fun getData() : LiveData<List<Item>> = bookMarkLiveData
    fun getEventData() : LiveData<EventEnum> = eventLiveData

    fun call(){
        eventLiveData.value = null
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}