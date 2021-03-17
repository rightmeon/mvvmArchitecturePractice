package com.example.mvvmpj.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel(){
    protected val compositeDisposable = CompositeDisposable();
}