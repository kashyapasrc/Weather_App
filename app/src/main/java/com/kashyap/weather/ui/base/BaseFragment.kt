package com.kashyap.weather.ui.base

import android.view.animation.Animation
import androidx.fragment.app.Fragment
import com.labo.kaji.fragmentanimations.FlipAnimation


open class BaseFragment : Fragment() {


    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return if (enter) {
            FlipAnimation.create(FlipAnimation.LEFT, enter, 500)
        } else {
            FlipAnimation.create(FlipAnimation.RIGHT, enter, 500)
        }
    }

    open fun displayWarningMsg(message: String?) {
        if (activity != null && activity is BaseActivity) {
            (activity as BaseActivity?)!!.displayWarningMsg(message)
        }
    }

    open fun displaySuccessMsg(message: String?) {
        if (activity != null && activity is BaseActivity) {
            (activity as BaseActivity?)!!.displaySuccessMsg(message)
        }
    }

    open fun displayInfoMsg(message: String?) {
        if (activity != null && activity is BaseActivity) {
            (activity as BaseActivity?)!!.displayInfoMsg(message)
        }
    }

    open fun dispalyErrorMsg(message: String?) {
        if (activity != null && activity is BaseActivity) {
            (activity as BaseActivity?)!!.dispalyErrorMsg(message)
        }
    }


}