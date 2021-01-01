package com.kashyap.weather.ui.home

import com.kashyap.weather.domain.models.BookMarkModel

public interface IBookmarksClickListener {

    fun onDeleteClick(bookMarkModel: BookMarkModel);

    fun onItemSelected(bookMarkModel: BookMarkModel);
}