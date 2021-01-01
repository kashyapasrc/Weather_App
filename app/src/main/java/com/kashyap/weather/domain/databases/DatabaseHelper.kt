package com.kashyap.weather.domain.databases

import com.kashyap.weather.domain.databases.entity.BookMarks

interface DatabaseHelper {

    suspend fun getAllBookMarks(): List<BookMarks>

    suspend fun insertBookMark(bookMark: BookMarks)

    suspend fun deleteBookMark(id : Int)
}