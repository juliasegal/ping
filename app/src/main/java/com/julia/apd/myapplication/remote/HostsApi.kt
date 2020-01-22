package com.julia.apd.myapplication.remote

import com.julia.apd.myapplication.remote.responsemodels.HostResponseModel
import retrofit2.http.*

interface HostsApi {

    @GET("sk_hosts")
    suspend fun getHosts(): List<HostResponseModel>

    companion object {
        const val url = " https://gist.githubusercontent.com/anonymous/290132e587b77155eebe44630fcd12cb/raw/777e85227d0c1c16e466475bb438e0807900155c/"
    }

}