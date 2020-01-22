package com.julia.apd.myapplication.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.julia.apd.myapplication.models.HostsModel
import com.julia.apd.myapplication.networking.ResponseHandler
import com.julia.apd.myapplication.remote.HostsApi

interface HostsRepository {
    suspend fun getHosts(): LiveData<Resource<HostsModel>>
}

class HostsRepositoryImpl(
    private val hostsApi: HostsApi,
    private val responseHandler: ResponseHandler
) : HostsRepository {

    override suspend fun getHosts(): LiveData<Resource<HostsModel>> {
        val hostsData = MutableLiveData<Resource<HostsModel>>()
        try {
            val response = hostsApi.getHosts()
            hostsData.value = responseHandler.handleSuccess(HostsModel.map(response))
        } catch (ex: Exception) {
            hostsData.value = responseHandler.handleException(ex)
        }
        return hostsData
    }
}
