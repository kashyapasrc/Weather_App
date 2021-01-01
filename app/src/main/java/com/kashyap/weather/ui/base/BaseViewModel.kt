package com.kashyap.weather.ui.base

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import org.json.JSONObject
import retrofit2.HttpException

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected val errorResponse = MutableLiveData<String>()

    open val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("STUDY_R", "Message: ${throwable.localizedMessage}")

        var msg = "Message: ${throwable.localizedMessage}"
        if (throwable is HttpException) {
            val body = throwable.response()!!.errorBody()
            try {
                val jObjError = JSONObject(body!!.string())
                System.out.print(jObjError.toString())
                // mView.showError(jObjError.getString("message"))
                if (jObjError.has("message")) {
                    System.out.println(jObjError.getString("message"))
                    msg = (jObjError.getString("message"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }
        errorResponse.postValue(msg)
    }

    fun getErrorResponse(): LiveData<String> = errorResponse

}