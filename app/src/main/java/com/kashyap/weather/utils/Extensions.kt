package com.kashyap.weather.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.io.File



inline fun <reified T : ViewModel> FragmentActivity.initViewModel(): T {
    return ViewModelProviders.of(this).get(T::class.java)
}
inline fun <reified T : ViewModel> Fragment.initViewModel(): T {
    return ViewModelProviders.of(this).get(T::class.java)
}



val FragmentActivity.scope: CoroutineScope
    get() = CoroutineScope(
        Job() + Dispatchers.Main
    )