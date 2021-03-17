package com.example.mvvmpj.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.NullPointerException
import javax.inject.Inject
import javax.inject.Provider


class ViewModelFactory @Inject constructor(var viewModelMap : MutableMap<Class<out ViewModel>, Provider<ViewModel>>)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        viewModelMap[modelClass]?.get() as T ?: throw NullPointerException("viewModel is null")

}