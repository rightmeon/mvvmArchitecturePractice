package com.example.mvvmpj.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmpj.api.NaverBookAPI
import com.example.mvvmpj.di.ActivityScope
import com.example.mvvmpj.di.ViewModelKey
import com.example.mvvmpj.repository.BookMarkDao
import com.example.mvvmpj.viewModel.BookMarkViewModel
import com.example.mvvmpj.viewModel.BookViewModel
import com.example.mvvmpj.viewModel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @ActivityScope
    abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory

    companion object{
        @Provides
        @IntoMap
        @ActivityScope
        @ViewModelKey(BookViewModel::class)
        fun bookViewModel(api: NaverBookAPI) : ViewModel = BookViewModel(api)

        @Provides
        @IntoMap
        @ActivityScope
        @ViewModelKey(BookMarkViewModel::class)
        fun bookMarkViewModel(dao: BookMarkDao) : ViewModel = BookMarkViewModel(dao)
    }
}