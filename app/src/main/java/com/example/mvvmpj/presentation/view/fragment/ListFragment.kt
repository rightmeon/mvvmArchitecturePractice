package com.example.mvvmpj.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mvvmpj.presentation.adapters.BookAdapter
import com.example.mvvmpj.databinding.FragmentListBinding
import com.example.mvvmpj.presentation.viewmodel.BookViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject
import javax.inject.Named

class ListFragment : DaggerFragment() {
    @Inject
    lateinit var binding : FragmentListBinding
    @Inject
    @Named("BookViewModelAdapter")
    lateinit var bookAdapter : BookAdapter
    @Inject
    lateinit var viewModel : BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.apply {
            fragment = this@ListFragment
            viewmodel = viewModel
            bookList.addItemDecoration(DividerItemDecoration(context, 1))
            bookList.adapter = bookAdapter
            lifecycleOwner = this@ListFragment
        }.root
    }

    fun clickListener(view: View) {
        viewModel.searchBookList(edit_writer.text.toString())
        edit_writer.setText("")
    }
}