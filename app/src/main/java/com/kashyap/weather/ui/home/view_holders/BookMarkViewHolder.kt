package com.kashyap.weather.ui.home.view_holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kashyap.weather.databinding.ItemBookmarkBinding
import com.kashyap.weather.domain.models.BookMarkModel
import com.kashyap.weather.ui.home.IBookmarksClickListener

class BookMarkViewHolder(
    val binding: ItemBookmarkBinding,
    var iBookmarksClickListener: IBookmarksClickListener
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.iBookmarksClickListener = iBookmarksClickListener
    }

    fun onBind(bookMarkModel: BookMarkModel) {

        binding.setBookMarkModel(bookMarkModel);
        binding.executePendingBindings()
    }
}