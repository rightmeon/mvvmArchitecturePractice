package com.example.mvvmpj.view

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mvvmpj.R
import com.example.mvvmpj.databinding.ActivityMainBinding
import com.example.mvvmpj.viewModel.BookMarkViewModel
import com.example.mvvmpj.viewModel.BookViewModel
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(){
    @Inject
    lateinit var lazyBinding : Lazy<ActivityMainBinding>
    @Inject
    lateinit var lazyController : Lazy<NavController>
    @Inject
    lateinit var bookViewModel : BookViewModel
    @Inject
    lateinit var bookMarkViewModel: BookMarkViewModel

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = lazyBinding.get()
        navController = lazyController.get()

        binding.bottomNav.setupWithNavController(navController)
        setupActionBarWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.listFragment, R.id.bookMarkFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.detailFragment -> binding.bottomNav.visibility = View.GONE
            }
        }
    }
}


