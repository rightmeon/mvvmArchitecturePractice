package com.example.mvvmpj.presentation.di.module

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.mvvmpj.R
import com.example.mvvmpj.databinding.ActivityMainBinding
import com.example.mvvmpj.presentation.di.scope.ActivityScope
import com.example.mvvmpj.presentation.di.scope.FragmentScope
import com.example.mvvmpj.presentation.view.activity.MainActivity
import com.example.mvvmpj.presentation.view.fragment.BookMarkFragment
import com.example.mvvmpj.presentation.view.fragment.DetailFragment
import com.example.mvvmpj.presentation.view.fragment.ListFragment
import com.example.mvvmpj.presentation.viewmodel.BookMarkViewModel
import com.example.mvvmpj.presentation.viewmodel.BookViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class ActivityModule {

    companion object {
        @Provides
        @ActivityScope
        fun provideBinding(activity: MainActivity): ActivityMainBinding =
            DataBindingUtil.setContentView(activity, R.layout.activity_main)

        @Provides
        @ActivityScope
        fun navController(activity: MainActivity): NavController =
            (activity.supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
                .findNavController()

        @Provides
        @ActivityScope
        fun bookViewModelProvide(activity: MainActivity, factory: ViewModelProvider.Factory) : BookViewModel {
            return ViewModelProvider(activity, factory)[BookViewModel::class.java]
        }

        @Provides
        @ActivityScope
        fun bookMarkViewModelProvide(activity: MainActivity, factory: ViewModelProvider.Factory) : BookMarkViewModel {
            return ViewModelProvider(activity, factory)[BookMarkViewModel::class.java]
        }
    }

    @FragmentScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun listFragment(): ListFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun detailFragment(): DetailFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun bookMarkFragment(): BookMarkFragment

}