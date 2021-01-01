package com.kashyap.weather.domain.models

import android.os.Parcel
import android.os.Parcelable

data class BookMarkModel(
    val id: Int,
    val name: String?,
    val latitude: Double,
    val longitude: Double,
    val isBookMarked: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeByte(if (isBookMarked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookMarkModel> {
        override fun createFromParcel(parcel: Parcel): BookMarkModel {
            return BookMarkModel(parcel)
        }

        override fun newArray(size: Int): Array<BookMarkModel?> {
            return arrayOfNulls(size)
        }
    }
}