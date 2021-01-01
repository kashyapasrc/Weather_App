package com.kashyap.weather.domain.databases.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

import com.kashyap.weather.domain.databases.entity.BookMarks

@Dao
interface BookMarksDao {

    @Query("SELECT * FROM BookMarks")
    suspend fun getAllBookMarks(): List<BookMarks>

    @Insert
    suspend fun insertBookMark(bookMark: BookMarks)

    @Query("DELETE FROM BookMarks WHERE id = :Id")
    suspend fun deleteById(Id: Int)


}