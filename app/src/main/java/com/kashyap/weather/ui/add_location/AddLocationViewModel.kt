package com.kashyap.weather.ui.add_location

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashyap.weather.domain.databases.DatabaseHelper
import com.kashyap.weather.domain.databases.entity.BookMarks
import com.kashyap.weather.domain.models.BookMarkModel
import com.kashyap.weather.domain.repository.BookMarkRepository
import com.kashyap.weather.ui.base.BaseViewModel
import com.mindorks.example.coroutines.utils.Resource
import kotlinx.coroutines.launch

class AddLocationViewModel(application: Application) : BaseViewModel(application) {

    private var repo: BookMarkRepository? = null

    init {
        repo = BookMarkRepository.getInstance(application)
    }

    private val status = MutableLiveData<Resource<Boolean>>()


    fun insertBookMarkRecord(bookMark: BookMarks) {
        viewModelScope.launch {
            status.postValue(Resource.loading(null))
            try {
                repo!!.insertBookMark(bookMark)
                status.postValue(Resource.success(true))
            } catch (e: Exception) {
                status.postValue(Resource.error("Something Went Wrong", null))
            }
        }
    }

    fun getRecordStatus(): LiveData<Resource<Boolean>> {
        return status
    }
}
