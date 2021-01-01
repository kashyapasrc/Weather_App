package com.kashyap.weather.ui.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kashyap.weather.R
import com.kashyap.weather.ui.base.BaseFragment

class MoreFragment : BaseFragment() {

    lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_more, container, false)
        initViews()
        return rootView
    }


    private fun initViews() {

    }

}