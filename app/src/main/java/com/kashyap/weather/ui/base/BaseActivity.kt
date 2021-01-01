package com.kashyap.weather.ui.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.kashyap.weather.utils.DisplaySnackMessage

open class BaseActivity : AppCompatActivity() {

    protected var parentLayout: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentLayout = findViewById<android.view.View?>(android.R.id.content)
    }


    protected open fun setFlags() {
        //requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
    fun displayWarningMsg(message: String?) {
        parentLayout?.let { DisplaySnackMessage.displayWarningMsg(it, message) }
    }

    fun displaySuccessMsg(message: String?) {
        parentLayout?.let { DisplaySnackMessage.displaySuccesMsg(it, message) }
    }

    fun displayInfoMsg(message: String?) {
        parentLayout?.let { DisplaySnackMessage.displayInfoMsg(it, message) }
    }

    fun dispalyErrorMsg(message: String?) {
        parentLayout?.let { DisplaySnackMessage.displaySnackErrorMsg(it, message) }
    }

    fun getContext(): Context? {
        return this
    }

    fun getActivity(): Activity? {
        return this
    }

    fun displayNoInternet() {
        DisplaySnackMessage.showNetworkAlert(parentLayout)
    }

    fun startActivityWithAnim(intent: Intent?) {
        startActivity(intent)
        //overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
    }


}