package com.example.mvvmpj.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmpj.data.model.Item
import com.example.mvvmpj.domain.BookRepository
import javax.inject.Inject

class BookViewModel @Inject constructor(private val bookRepository: BookRepository) : BaseViewModel() {
    private var liveData = MutableLiveData<List<Item>>()

    fun searchBookList(query : String) {
        compositeDisposable.add(bookRepository.searchBookList(query)
            .subscribe({liveData.value = it}, { it.printStackTrace()}))
    }

    fun getData() : LiveData<List<Item>> = liveData

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}