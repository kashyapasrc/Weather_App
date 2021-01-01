package com.kashyap.weather.domain.repository

import android.content.Context
import com.kashyap.weather.domain.databases.AppDatabase
import com.kashyap.weather.domain.databases.DatabaseBuilder
import com.kashyap.weather.domain.databases.DatabaseHelper
import com.kashyap.weather.domain.databases.DatabaseHelperImpl
import com.kashyap.weather.domain.databases.dao.BookMarksDao
import com.kashyap.weather.domain.databases.entity.BookMarks

class BookMarkRepository {


    companion object {
        private lateinit var dbHelper: DatabaseHelper
        var instance: BookMarkRepository? = null;
        fun getInstance(context: Context): BookMarkRepository {
            if (instance == null) {
                instance = BookMarkRepository()
                dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(context))
            }
            return instance as BookMarkRepository;
        }
    }


    suspend fun fetchBookMarkList(): List<BookMarks> {
        return dbHelper.getAllBookMarks()
    }

    suspend fun deleteBookMark(id: Int) {
        dbHelper.deleteBookMark(id)
    }

    suspend fun insertBookMark(bookMarks: BookMarks) {
        dbHelper.insertBookMark(bookMarks)
    }
}