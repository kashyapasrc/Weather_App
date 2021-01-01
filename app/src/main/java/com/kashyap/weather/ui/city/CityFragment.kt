package com.kashyap.weather.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kashyap.weather.R
import com.kashyap.weather.databinding.FragmentCityScreenBinding
import com.kashyap.weather.domain.constants.BundleConstants
import com.kashyap.weather.domain.models.BookMarkModel
import com.kashyap.weather.domain.networking.response.forecast_info.Forecast
import com.kashyap.weather.domain.networking.response.forecast_info.ForecastInfo
import com.kashyap.weather.domain.networking.response.weather_update.WeatherUpdate
import com.kashyap.weather.ui.base.BaseFragment
import com.kashyap.weather.ui.city.adapters.ForecastAdapter
import com.kashyap.weather.utils.DisplaySnackMessage
import com.kashyap.weather.utils.NetworkUtils
import com.kashyap.weather.utils.initViewModel
import com.mindorks.example.coroutines.utils.Status

class CityFragment : BaseFragment() {

    private lateinit var mForecastAdapter: ForecastAdapter

    private lateinit var mBinding: FragmentCityScreenBinding
    private lateinit var rootView: View
    private lateinit var mCityViewModel: CityViewModel
    private lateinit var bookMarkModel: BookMarkModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var list: ArrayList<Forecast>

    companion object {
        private val TAG = CityFragment::class.java.simpleName
        fun getInstance(bookMarkModel: BookMarkModel): CityFragment {
            val fragment = CityFragment();
            val bundle = Bundle()
            bundle.putParcelable(BundleConstants.BOOKMARKSMODEL, bookMarkModel)
            fragment.arguments = bundle;
            return fragment;
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = DataBindingUtil.inflate<FragmentCityScreenBinding>(
            inflater,
            R.layout.fragment_city_screen,
            container,
            false
        )
        rootView = mBinding.root;
        getBundleArguments()
        initViews()
        setupViewModel()
        setupObserver()

        return rootView
    }

    private fun initViews() {
        mRecyclerView = mBinding.forecastRecycler
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        list = ArrayList<Forecast>()
        mForecastAdapter = ForecastAdapter(list = list)
        mRecyclerView.adapter = mForecastAdapter
        mRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!NetworkUtils.isNetworkAvailable(context)) {
            DisplaySnackMessage.showNetworkAlert(rootView)
            return
        }
        mCityViewModel.fetchWeatherUpdates(bookMarkModel)
        mCityViewModel.fetchForecastInfo(bookMarkModel)
    }

    private fun getBundleArguments() {
        if (arguments != null) {
            bookMarkModel =
                requireArguments().getParcelable<BookMarkModel>(BundleConstants.BOOKMARKSMODEL)!!
        }
    }

    private fun setupObserver() {
        mCityViewModel.getWeatherUpdates().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { weatherUpdate -> //renderList(users)
                        updateUI(weatherUpdate);
                    }
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    //Handle Error
                    dispalyErrorMsg(it.message)
                }
            }
        })
        mCityViewModel.getForecastInfo().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { forecastInfo -> //renderList(users)
                        updateList(forecastInfo);
                    }
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    //Handle Error
                    dispalyErrorMsg(it.message)
                }
            }
        })

    }

    private fun updateList(forecastInfo: ForecastInfo) {
        mForecastAdapter.setNewList(forecastInfo.list)
    }

    private fun updateUI(weatherUpdate: WeatherUpdate) {
        mBinding.weatherUpdate = weatherUpdate;
        try {
            mBinding.weatherTv.text = weatherUpdate.weather[0].main
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val temp = (weatherUpdate.main.temp / 10).toInt();
        mBinding.tempCentigradeTv.text = getString(R.string.centigrade, temp);
        mBinding.wind.text =
            getString(R.string.wind, String.format(".1f", weatherUpdate.wind.speed))
        mBinding.humidity.text = getString(R.string.humidity, weatherUpdate.main.humidity)
        mBinding.uvIndex.text = getString(R.string.uv_index)
        mBinding.pressure.text = getString(R.string.pressure, weatherUpdate.main.pressure)
        val visiblie = (weatherUpdate.visibility / 1000).toFloat();
        mBinding.visibility.text =
            getString(R.string.visibility, String.format("%.2f", visiblie))


    }

    private fun setupViewModel() {
        mCityViewModel = initViewModel()
    }
}