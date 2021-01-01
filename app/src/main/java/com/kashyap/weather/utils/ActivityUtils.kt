package com.kashyap.weather.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kashyap.weather.utils.KeyBoardUtils.hideKeyBoard

object ActivityUtils {
    fun replaceContentFragment(
        fragmentManager: FragmentManager,
        @IdRes layoutId: Int,
        fragment: Fragment, addToBackStack: Boolean
    ) {
        if (fragment.activity != null) hideKeyBoard(fragment.activity)
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(layoutId, fragment, fragment.javaClass.simpleName)
        if (addToBackStack) transaction.addToBackStack(fragment.javaClass.simpleName)
        transaction.commit()
    }

    fun findFragmentById(fragmentManager: FragmentManager, @IdRes layoutId: Int): Fragment? {
        return fragmentManager.findFragmentById(layoutId)
    }
}