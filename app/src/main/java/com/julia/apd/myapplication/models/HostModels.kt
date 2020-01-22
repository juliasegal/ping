package com.julia.apd.myapplication.models

import com.julia.apd.myapplication.remote.responsemodels.HostResponseModel

data class HostModel(val name: String, val url: String, val iconUrl: String, var latency: Float? = null)

data class HostsModel(val hosts: List<HostModel>) {
    companion object {
        fun map(response: List<HostResponseModel>) = HostsModel(response.map {
            HostModel(it.name, it.url, it.iconUrl)
        })
    }
}

