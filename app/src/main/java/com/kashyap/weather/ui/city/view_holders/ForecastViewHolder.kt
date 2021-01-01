package com.kashyap.weather.ui.city.view_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kashyap.weather.R
import com.kashyap.weather.domain.networking.response.forecast_info.Forecast
import com.kashyap.weather.utils.DateUtils

class ForecastViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
    val mDateTextView: TextView;
    val mTemperatureTextView: TextView;
    val mClimateStatus: ImageView

    init {
        mDateTextView = itemView.findViewById<TextView>(R.id.date);
        mTemperatureTextView = itemView.findViewById<TextView>(R.id.temperature);
        mClimateStatus = itemView.findViewById<ImageView>(R.id.climate_logo)
    }

    fun onBind(forecast: Forecast) {
        mDateTextView.text = forecast.dt_txt //DateUtils.convertDate(forecast.dt_txt)

        val temp_max = (forecast.main.temp_max / 10).toInt()
        val temp_min = (forecast.main.temp_min / 10).toInt()
        mTemperatureTextView.text =
            itemView.resources.getString(R.string.two_centigrade, temp_max, temp_min)
        when (forecast.weather[0].main) {
            "Clear" -> {
                mClimateStatus.setImageResource(R.drawable.ic_baseline_cloud_24);
            }
            "Clouds" -> {
                mClimateStatus.setImageResource(R.drawable.ic_baseline_cloud_24);
            }
            "sun" -> {
                mClimateStatus.setImageResource(R.drawable.ic_baseline_wb_sunny_24);
            }

        }
    }
}