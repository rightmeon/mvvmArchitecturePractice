package com.example.mvvmpj.di.module

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.mvvmpj.R
import com.example.mvvmpj.databinding.ActivityMainBinding
import com.example.mvvmpj.di.ActivityScope
import com.example.mvvmpj.di.FragmentScope
import com.example.mvvmpj.view.BookMarkFragment
import com.example.mvvmpj.view.DetailFragment
import com.example.mvvmpj.view.ListFragment
import com.example.mvvmpj.view.MainActivity
import com.example.mvvmpj.viewModel.BookMarkViewModel
import com.example.mvvmpj.viewModel.BookViewModel
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