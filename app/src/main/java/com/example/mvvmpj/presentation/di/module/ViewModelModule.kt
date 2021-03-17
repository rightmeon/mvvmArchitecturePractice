package com.example.mvvmpj.presentation.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmpj.domain.BookRepositoryImpl
import com.example.mvvmpj.presentation.di.scope.ActivityScope
import com.example.mvvmpj.presentation.di.scope.ViewModelKey
import com.example.mvvmpj.presentation.viewmodel.BookMarkViewModel
import com.example.mvvmpj.presentation.viewmodel.BookViewModel
import com.example.mvvmpj.presentation.viewmodel.ViewModelFactory
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
        fun bookViewModel(repo : BookRepositoryImpl) : ViewModel = BookViewModel(repo)

        @Provides
        @IntoMap
        @ActivityScope
        @ViewModelKey(BookMarkViewModel::class)
        fun bookMarkViewModel(repo : BookRepositoryImpl) : ViewModel = BookMarkViewModel(repo)
    }
}