package com.kashyap.weather.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kashyap.weather.R
import com.kashyap.weather.databinding.ItemBookmarkBinding
import com.kashyap.weather.domain.models.BookMarkModel
import com.kashyap.weather.ui.home.IBookmarksClickListener
import com.kashyap.weather.ui.home.view_holders.BookMarkViewHolder

class BookMarkAdapter(var list: ArrayList<BookMarkModel>, var iBookmarksClickListener : IBookmarksClickListener) :
    RecyclerView.Adapter<BookMarkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookMarkViewHolder {


        val binding = DataBindingUtil.inflate<ItemBookmarkBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_bookmark, parent, false
        ) as ItemBookmarkBinding

        return BookMarkViewHolder(binding,iBookmarksClickListener);

    }

    override fun onBindViewHolder(holder: BookMarkViewHolder, position: Int) {
        holder.onBind(list.get(position));
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    fun setNewList(list: ArrayList<BookMarkModel>) {
        this.list = list;
        notifyDataSetChanged()
    }
}