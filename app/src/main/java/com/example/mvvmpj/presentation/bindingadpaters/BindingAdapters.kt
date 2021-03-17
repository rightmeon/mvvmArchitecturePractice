package com.example.mvvmpj.repository

import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmpj.presentation.adapters.BookAdapter
import com.example.mvvmpj.data.model.Item

@BindingAdapter("loadImage")
fun loadImage(view : ImageView, image : String){
    Glide.with(view).load(image).override(80,100).into(view)
}

@BindingAdapter("loadBigImage")
fun loadBigImage(view : ImageView, image : String){
    Glide.with(view).load(image).override(251,300).into(view)
}

@BindingAdapter("br_text")
fun cleanBrTag(view : TextView, brString : String){
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        view.text = Html.fromHtml(brString, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        view.text = Html.fromHtml(brString).toString()
    }
}

@BindingAdapter("bind_booklist")
fun bindBookList(view : RecyclerView, list : LiveData<List<Item>>){
    val adapter = view.adapter
    if(adapter is BookAdapter){
        adapter.setItemList(list.value)
        adapter.notifyDataSetChanged()
    }
}