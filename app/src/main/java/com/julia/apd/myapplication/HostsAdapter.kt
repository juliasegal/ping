package com.julia.apd.myapplication

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.julia.apd.myapplication.models.HostModel

class HostsAdapter : RecyclerView.Adapter<HostViewHolder>() {
    private var hosts: List<HostModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HostViewHolder {
        return HostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.host_item, parent, false)
        )
    }

    fun setItems(hosts: List<HostModel>?) {
        this.hosts = hosts
        notifyDataSetChanged()
    }

    fun updateLatency(index: Int, latency: Float?) {
        val size = hosts?.size ?: -1
        if (size > index) {
            hosts?.get(index)?.latency = latency
            notifyItemChanged(index)
        }
    }

    private fun getItem(position: Int): HostModel? {
        val size = hosts?.size ?: -1
        return if (size > position) {
            hosts?.get(position)
        } else {
            null
        }
    }

    override fun getItemCount() = hosts?.size ?: 0

    override fun onBindViewHolder(holder: HostViewHolder, position: Int) {
        val hostModel = getItem(position)
        if (hostModel != null) {
            holder.bind(hostModel)
        }
    }
}

class HostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(hostModel: HostModel) {
        val nameView = itemView.findViewById<TextView>(R.id.name)
        val latency = itemView.findViewById<TextView>(R.id.latency)
        val hostIconUrl = itemView.findViewById<ImageView>(R.id.host_icon)

        nameView.text = hostModel.name
        if (hostModel.latency == null) {
            latency.setText(R.string.loading)
            latency.setTextColor(Color.GREEN)
        } else {
            if (hostModel.latency == -1F) {
                latency.setText(R.string.not_found)
                latency.setTextColor(Color.RED)
            } else {
                latency.text = hostModel.latency.toString()
                latency.setTextColor(Color.BLACK)
            }
        }

        Glide.with(hostIconUrl.context)
            .load(hostModel.iconUrl)
            .error(ColorDrawable(Color.WHITE))
            .into(hostIconUrl)
    }
}

