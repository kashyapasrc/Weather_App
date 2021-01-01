package com.kashyap.weather.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private const val INPUT_FORMAT = "yyyy-MM-dd HH:mm:ss"
    private const val OUTPUT_FORMAT = "dd MMMM yyyy"


    fun convertDate(dateInput: String?): String {
        return try { //"2021-01-01 15:00:00"
            val originalFormat: DateFormat = SimpleDateFormat(INPUT_FORMAT, Locale.ENGLISH)
            val targetFormat: DateFormat = SimpleDateFormat(OUTPUT_FORMAT)
            val date = originalFormat.parse(dateInput)
            targetFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            ""
        }
    }
}