package com.kashyap.weather.domain.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kashyap.weather.domain.databases.dao.BookMarksDao
import com.kashyap.weather.domain.databases.entity.BookMarks

@Database(entities = [BookMarks::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookMarksDao(): BookMarksDao


}