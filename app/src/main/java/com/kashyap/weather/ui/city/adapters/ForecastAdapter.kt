package com.kashyap.weather.ui.city.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kashyap.weather.R
import com.kashyap.weather.domain.networking.response.forecast_info.Forecast
import com.kashyap.weather.ui.city.view_holders.ForecastViewHolder

class ForecastAdapter(var list: List<Forecast>) : RecyclerView.Adapter<ForecastViewHolder>() {


    private var mLayoutInflater: LayoutInflater? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        if (mLayoutInflater == null)
            mLayoutInflater = (LayoutInflater.from(parent.context))
        val itemView = mLayoutInflater!!.inflate(R.layout.item_forecast, parent, false);
        return ForecastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.onBind(list.get(position));
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setNewList(list: List<Forecast>) {
        this.list = list;
        notifyDataSetChanged()
    }
}