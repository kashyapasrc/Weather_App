package com.kashyap.weather.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.kashyap.weather.R
import com.kashyap.weather.domain.models.BookMarkModel
import com.kashyap.weather.ui.add_location.AddLocationFragment
import com.kashyap.weather.ui.base.BaseActivity
import com.kashyap.weather.ui.base.BaseFragment
import com.kashyap.weather.ui.city.CityFragment
import com.kashyap.weather.ui.home.HomeFragment
import com.kashyap.weather.utils.ActivityUtils

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        //if (supportActionBar != null) supportActionBar!!.hide()
        setHomeFragment()

    }

    fun setGoogleMap() {
        replaceContentFragment(AddLocationFragment.getInstance(), true)
        setFragmentTitle(getString(R.string.add_city))
    }

    fun setHomeFragment() {
        replaceContentFragment(HomeFragment.getInstance(), true)
        setFragmentTitle(getString(R.string.home_screen))
    }

    fun setCityScreen(bookMarkModel: BookMarkModel) {
        replaceContentFragment(CityFragment.getInstance(bookMarkModel), true)
        bookMarkModel.name?.let { setFragmentTitle(it) }
    }

    fun replaceContentFragment(fragment: BaseFragment, addToBackStack: Boolean) {
        ActivityUtils.replaceContentFragment(
            supportFragmentManager,
            R.id.container,
            fragment, addToBackStack
        )
    }

    fun setFragmentTitle(title: String) {
        addToolbarWithTitle(true, title)
    }

    protected fun addToolbarWithTitle(showBackArrow: Boolean, title: String?) {
        if (supportActionBar != null) {
            if (showBackArrow) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                supportActionBar!!.setDisplayShowHomeEnabled(true)
            }
            if (title != null && !title.isEmpty()) supportActionBar!!.title = title


        }
    }
}