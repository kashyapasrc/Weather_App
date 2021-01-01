package com.kashyap.weather.ui.home

import android.app.Application
import androidx.lifecycle.*

import com.kashyap.weather.domain.databases.DatabaseHelper
import com.kashyap.weather.domain.databases.entity.BookMarks
import com.kashyap.weather.domain.models.BookMarkModel
import com.kashyap.weather.domain.repository.BookMarkRepository
import com.kashyap.weather.ui.base.BaseViewModel
import com.mindorks.example.coroutines.utils.Resource
import kotlinx.coroutines.launch

class BookMarkViewModel(application: Application) : BaseViewModel(application) {
    private val users = MutableLiveData<Resource<ArrayList<BookMarkModel>>>()
    private var repo: BookMarkRepository? = null

    init {
        repo = BookMarkRepository.getInstance(application)
        fetchBookMarks()
    }

    fun getBookMarks(): LiveData<Resource<ArrayList<BookMarkModel>>> {
        return users
    }

    fun deleteBookMarks(bookMarkModel: BookMarkModel) {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            try {
                repo!!.deleteBookMark(bookMarkModel.id)
                val bookMarksFromDB = repo!!.fetchBookMarkList()
                parseDBtoModel(bookMarksFromDB)

            } catch (e: Exception) {
                users.postValue(Resource.error("Something Went Wrong", null))
            }
        }
    }

    fun fetchBookMarks() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            try {
                val bookMarksFromDB =
                    repo!!.fetchBookMarkList()
                parseDBtoModel(bookMarksFromDB)
            } catch (e: Exception) {
                users.postValue(Resource.error("Something Went Wrong", null))
            }
        }
    }

    fun parseDBtoModel(bookMarksFromDB: List<BookMarks>) {
        val list = ArrayList<BookMarkModel>()
        if (!bookMarksFromDB.isEmpty()) {
            for (bookMark in bookMarksFromDB) {
                val bookMarkModel = BookMarkModel(
                    bookMark.id,
                    bookMark.name,
                    bookMark.latitude,
                    bookMark.longitude,
                    bookMark.bookMark
                )
                list.add(bookMarkModel)
            }

            users.postValue(Resource.success(list))

        } else {
            users.postValue(Resource.success(list))
        }

    }
}

