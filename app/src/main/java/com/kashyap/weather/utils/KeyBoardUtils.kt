package com.kashyap.weather.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.View.OnTouchListener
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

object KeyBoardUtils {
    fun hideKeyboardSpinner(spinner: Spinner?, activity: Activity?) {
        if (spinner == null) return
        spinner.setOnTouchListener(OnTouchListener { v, event ->
            hideKeyBoard(activity)
            false
        })
    }

    /**
     * hides the keyboard
     *
     * @param activity
     */
    fun hideKeyBoard(activity: Activity?) {
        try {
            if (activity != null) {
                val inputManager =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                val currentFocus = activity.currentFocus
                if (currentFocus != null) {
                    currentFocus.clearFocus()
                    assert(inputManager != null)
                    inputManager.hideSoftInputFromWindow(
                        activity.currentFocus!!.windowToken,
                        InputMethodManager.HIDE_NOT_ALWAYS
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun hideKeyBoard(activity: Activity?, focusedView: View?) {
        try {
            if (activity != null) {
                val inputManager =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                //View currentFocus = activity.getCurrentFocus();
                if (focusedView != null) {
                    focusedView.clearFocus()
                    assert(inputManager != null)
                    inputManager.hideSoftInputFromWindow(
                        focusedView.windowToken,
                        InputMethodManager.HIDE_NOT_ALWAYS
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun hideKeyBoard(context: Context?, textView: TextView) {
        try {
            if (context != null) {
                val inputManager =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager?.hideSoftInputFromWindow(
                    textView.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun hideKeyboardWithFocus(context: Activity?) {
        if (context != null) {
            val inputManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val currentFocus = context.currentFocus
            if (currentFocus != null) {
                inputManager.hideSoftInputFromWindow(
                    context.currentFocus!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
                if (currentFocus is EditText) {
                    currentFocus.isCursorVisible = false
                    currentFocus.setOnClickListener(View.OnClickListener { v ->
                        if (v != null) {
                            (v as EditText).isCursorVisible = true
                        }
                    })
                }
            }
        }
    }

    fun hideSoftKeyboard(context: Activity?) {
        if (context != null) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (context.currentFocus != null) {
                imm.hideSoftInputFromWindow(context.currentFocus!!.windowToken, 0)
                //            mContext.getCurrentFocus().clearFocus();
            }
        }
    }

    fun hideSoftKeyboard(view: View, context: Activity) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.applicationWindowToken, 0)
    }

    fun showKeypad(editText: EditText, context: Activity) {
        editText.requestFocus()
        editText.isCursorVisible = true
        editText.isClickable = true
        editText.isEnabled = true
        editText.setSelection(editText.text.length)
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED)
    }

    fun hidKeyBoardInDialog(dialog: Dialog?, context: Context) {
        try {
            if (dialog != null) {
                val imm =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(dialog.window!!.decorView.windowToken, 0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}