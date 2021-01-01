package com.kashyap.weather.domain.databases.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookMarks(

    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "bookmark") val bookMark: Boolean,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0



}