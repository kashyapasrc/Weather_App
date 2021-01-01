package com.kashyap.weather.utils

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.kashyap.weather.R

object DisplaySnackMessage {
    fun showNetworkAlert(view: View?) {
        if (view == null) return
        val message = view.context.getString(R.string.please_check_your_internet_connection)
        displaySnackBar(view, R.color.error_color, message)
    }

    fun displaySnackErrorMsg(view: View, msg: String?) {
        displaySnackBar(view, R.color.error_color, msg)
    }

    fun displayInfoMsg(view: View, msg: String?) {
        displaySnackBar(view, R.color.info_color, msg)
    }

    fun displaySuccesMsg(view: View, msg: String?) {
        displaySnackBar(view, R.color.success_color, msg)
    }

    fun displayWarningMsg(view: View, msg: String?) {
        displaySnackBar(view, R.color.warning_color, msg)
    }

    fun displaySnackBar(view: View, color: Int, message: String?) {
        try {
            if (message == null || message.isEmpty()) return
            val mSnackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            mSnackbar.view.setBackgroundColor(ContextCompat.getColor(view.context, color))
            mSnackbar.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun dipslaySnackBarWithButton(
        view: View,
        message: String?,
        buttonText: String?,
        iSnackBarAction: ISnackBarAction?
    ) {
        try {
            if (message == null || message.isEmpty()) return
            val mSnackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction(buttonText) { iSnackBarAction?.onSnackButtonClicked() }
            mSnackbar.view.setBackgroundColor(
                ContextCompat.getColor(
                    view.context,
                    R.color.warning_color
                )
            )
            mSnackbar.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}