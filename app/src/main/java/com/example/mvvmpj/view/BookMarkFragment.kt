package com.example.mvvmpj.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mvvmpj.adapter.BookAdapter
import com.example.mvvmpj.databinding.FragmentBookMarkBinding
import com.example.mvvmpj.viewModel.BookMarkViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Named

class BookMarkFragment : DaggerFragment() {
    @Inject
    lateinit var binding : FragmentBookMarkBinding
    @Inject
    @Named("BookMarkViewModelAdapter")
    lateinit var bookMarkAdapter: BookAdapter
    @Inject
    lateinit var viewModel : BookMarkViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.apply {
            bookMarkList.addItemDecoration(DividerItemDecoration(context, 1))
            bookmarkviewmodel = viewModel
            bookMarkList.adapter = bookMarkAdapter
            lifecycleOwner = this@BookMarkFragment
        }.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getBookMarkList()
    }
}