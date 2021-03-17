package com.example.mvvmpj.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmpj.data.local.BookMarkDao
import com.example.mvvmpj.data.model.EventEnum
import com.example.mvvmpj.data.model.Item
import com.example.mvvmpj.domain.BookRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BookMarkViewModel @Inject constructor(private val bookRepository: BookRepository) : BaseViewModel() {
    private val bookMarkLiveData = MutableLiveData<List<Item>>()
    private val eventLiveData = MutableLiveData<EventEnum>()

    fun getBookMarkList() {
        compositeDisposable.add(bookRepository.getAllBookMarkList()
            .subscribeWith(object : DisposableSingleObserver<List<Item>>() {
                override fun onSuccess(t: List<Item>) {
                    bookMarkLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        )
    }

    fun insertBookMark(item : Item) {
        compositeDisposable.add(
            bookRepository.insertBookMark(item)
                .subscribe(
                    {eventLiveData.value = EventEnum.AddBookMark },
                    {eventLiveData.value = EventEnum.ErrorAddBookMark })
        )
    }

    fun deleteBookMark(isbn: String) {
        compositeDisposable.add(
            bookRepository.deleteBookMark(isbn)
                .subscribe(
                    {eventLiveData.value = EventEnum.DeleteBookMark },
                    {eventLiveData.value = EventEnum.ErrorDeleteBookMark })
        )
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