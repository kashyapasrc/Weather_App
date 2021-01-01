package com.kashyap.weather.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.kashyap.weather.R
import com.kashyap.weather.ui.MainActivity
import com.kashyap.weather.ui.base.BaseFragment
import com.kashyap.weather.ui.home.HomeFragment

class HelpFragment : BaseFragment() {

    private var webView: WebView? = null
    lateinit var rootView: View

    companion object {
        private val TAG = HelpFragment::class.java.simpleName
        public fun getInstance(): HelpFragment {
            val fragment = HelpFragment();
            return fragment;
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_help, container, false)
        initViews()
        return rootView
    }

    override fun onResume() {
        super.onResume()
        if (activity is MainActivity) {
            (activity as MainActivity).addToolbarWithTitle(true, getString(R.string.help))
        }
    }


    private fun initViews() {
        webView = rootView.findViewById<WebView>(R.id.webView)
        webView!!.settings.setJavaScriptEnabled(true)

        webView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true
            }
        }
        webView!!.loadUrl("file:///android_asset/help.html");   // now it will not fail here
    }


}