package com.codepalace.chatbot.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codepalace.chatbot.R
import kotlinx.android.synthetic.main.item_user_layout.view.*

class UserAdapter(val context: Context, val items: ArrayList<UserModelClass>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_user_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items.get(position)

        holder.tvId.text = item.id
        holder.tvName.text = item.name
        holder.tvCity.text = item.city
        holder.tvCountry.text = item.country
        holder.tvIata.text = item.iata
        holder.tvIcao.text = item.icao
        holder.tvLattitude.text = item.latitude


    }


    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvId = view.tv_id
        val tvName = view.tv_name
        val tvCity = view.tv_city
        val tvCountry = view.tv_country
        val tvIata = view.tv_iata
        val tvIcao = view.tv_icao
        val tvLattitude = view.tv_lattitude
    }
}