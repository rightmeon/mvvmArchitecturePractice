package com.example.mvvmpj.di.module

import android.view.LayoutInflater
import com.example.mvvmpj.adapter.BookAdapter
import com.example.mvvmpj.databinding.FragmentBookMarkBinding
import com.example.mvvmpj.databinding.FragmentDetailBinding
import com.example.mvvmpj.databinding.FragmentListBinding
import com.example.mvvmpj.di.FragmentScope
import com.example.mvvmpj.view.BookMarkFragment
import com.example.mvvmpj.view.DetailFragment
import com.example.mvvmpj.view.ListFragment
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