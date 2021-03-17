package com.example.mvvmpj.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.mvvmpj.databinding.FragmentDetailBinding
import com.example.mvvmpj.repository.EventEnum
import com.example.mvvmpj.repository.Item
import com.example.mvvmpj.viewModel.BookMarkViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DetailFragment : DaggerFragment() {
    private val args : DetailFragmentArgs by navArgs()
    @Inject
    lateinit var binding : FragmentDetailBinding
    @Inject
    lateinit var viewModel : BookMarkViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel.getEventData().observe(viewLifecycleOwner, Observer {
            if(it == null){
                return@Observer
            }
            Toast.makeText(context, it.msg, Toast.LENGTH_SHORT).show()
            when(it){
                EventEnum.DeleteBookMark -> (context as MainActivity).navController.popBackStack()
            }
            viewModel.call()
        })

        return binding.apply {
            item = args.bookItem
            detailfragment = this@DetailFragment
            descriptionText.movementMethod = ScrollingMovementMethod()
            if(args.check){
                binding.btnDeleteBookMark.visibility = View.GONE
                binding.btnBookMark.visibility = View.VISIBLE
            } else {
                binding.btnDeleteBookMark.visibility = View.VISIBLE
                binding.btnBookMark.visibility = View.GONE
            }
        }.root
    }

    fun onOutLinkClick(link : String){
        startActivity(Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(link)
        })
    }
    fun onBookMarkClick(item : Item){
        viewModel.insertBookMark(item)
    }
    fun deleteBookMark(isbn : String){
        viewModel.deleteBookMark(isbn)
    }
}