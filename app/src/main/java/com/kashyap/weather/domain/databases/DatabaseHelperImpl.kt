package com.kashyap.weather.domain.databases

import com.kashyap.weather.domain.databases.entity.BookMarks

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getAllBookMarks(): List<BookMarks> = appDatabase.bookMarksDao().getAllBookMarks()

    override suspend fun insertBookMark(bookMark: BookMarks) {
        appDatabase.bookMarksDao().insertBookMark(bookMark)
    }

    override suspend fun deleteBookMark(id: Int) {
        appDatabase.bookMarksDao().deleteById(id)
    }


}