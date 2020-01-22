package com.julia.apd.myapplication.remote.responsemodels

import com.google.gson.annotations.SerializedName

data class HostResponseModel(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    @SerializedName("icon") val iconUrl: String)


