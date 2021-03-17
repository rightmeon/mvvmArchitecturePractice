package com.example.mvvmpj.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpj.R
import com.example.mvvmpj.databinding.BookItemBinding
import com.example.mvvmpj.repository.Item
import com.example.mvvmpj.view.*

class BookAdapter(private val fragment : Fragment) : RecyclerView.Adapter<BookAdapter.BookHolder>() {

    private var bookList = listOf<Item>()

    class BookHolder(var binding : BookItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        return BookHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.book_item, parent, false))
    }

    override fun getItemCount(): Int = bookList.size

    fun setItemList(bookList : List<Item>?){
        this.bookList = bookList ?: emptyList()
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        bookList.let {
            holder.binding.item = it[position]
                holder.binding.adapter = this
        }
    }

    fun onItemClick(Item: Item){
        if(fragment is ListFragment){
            (fragment.context as MainActivity).navController.navigate(ListFragmentDirections.actionListFragmentToDetailFragment(Item, true))
        } else if(fragment is BookMarkFragment){
            (fragment.context as MainActivity).navController.navigate(BookMarkFragmentDirections.actionBookMarkFragmentToDetailFragment(Item, false))
        }
    }
}