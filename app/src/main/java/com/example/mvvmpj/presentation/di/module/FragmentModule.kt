package com.example.mvvmpj.presentation.di.module

import android.view.LayoutInflater
import com.example.mvvmpj.presentation.adapters.BookAdapter
import com.example.mvvmpj.databinding.FragmentBookMarkBinding
import com.example.mvvmpj.databinding.FragmentDetailBinding
import com.example.mvvmpj.databinding.FragmentListBinding
import com.example.mvvmpj.presentation.di.scope.FragmentScope
import com.example.mvvmpj.presentation.view.fragment.BookMarkFragment
import com.example.mvvmpj.presentation.view.fragment.DetailFragment
import com.example.mvvmpj.presentation.view.fragment.ListFragment
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class FragmentModule {

    @FragmentScope
    @Provides
    fun provideListBinding(fragment : ListFragment) : FragmentListBinding =
        FragmentListBinding.inflate(LayoutInflater.from(fragment.context))

    @FragmentScope
    @Provides
    fun provideDetailBinding(fragment: DetailFragment) : FragmentDetailBinding =
        FragmentDetailBinding.inflate(LayoutInflater.from(fragment.context))

    @FragmentScope
    @Provides
    fun provideBookMarkBinding(fragment: BookMarkFragment) : FragmentBookMarkBinding =
        FragmentBookMarkBinding.inflate(LayoutInflater.from(fragment.context))

    @FragmentScope
    @Provides
    @Named("BookViewModelAdapter")
    fun provideBookAdapter(fragment: ListFragment) : BookAdapter = BookAdapter(fragment)

    @FragmentScope
    @Provides
    @Named("BookMarkViewModelAdapter")
    fun provideBookMarkViewModel(fragment: BookMarkFragment) : BookAdapter = BookAdapter(fragment)
}