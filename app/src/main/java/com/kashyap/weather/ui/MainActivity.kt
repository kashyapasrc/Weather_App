package com.kashyap.weather.ui

import android.os.Bundle
import android.view.MenuItem
import com.kashyap.weather.R
import com.kashyap.weather.domain.models.BookMarkModel
import com.kashyap.weather.ui.add_location.AddLocationFragment
import com.kashyap.weather.ui.base.BaseActivity
import com.kashyap.weather.ui.base.BaseFragment
import com.kashyap.weather.ui.city.CityFragment
import com.kashyap.weather.ui.help.HelpFragment
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

    }

    fun setHomeFragment() {
        replaceContentFragment(HomeFragment.getInstance(), true)
    }

    fun setCityScreen(bookMarkModel: BookMarkModel) {
        replaceContentFragment(CityFragment.getInstance(bookMarkModel), true)

    }

    fun replaceContentFragment(fragment: BaseFragment, addToBackStack: Boolean) {
        ActivityUtils.replaceContentFragment(
            supportFragmentManager,
            R.id.container,
            fragment, addToBackStack
        )
    }


    fun addToolbarWithTitle(showBackArrow: Boolean, title: String?) {
        if (supportActionBar != null) {
            if (showBackArrow) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                supportActionBar!!.setDisplayShowHomeEnabled(true)
            }
            if (title != null && !title.isEmpty()) supportActionBar!!.title = title


        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun setHelpFragment() {
        replaceContentFragment(HelpFragment.getInstance(), true)
    }
}