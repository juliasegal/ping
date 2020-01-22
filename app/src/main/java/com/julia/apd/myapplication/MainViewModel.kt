package com.julia.apd.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julia.apd.myapplication.dao.HostsRepository
import com.julia.apd.myapplication.dao.Resource
import com.julia.apd.myapplication.models.HostModel
import com.julia.apd.myapplication.models.HostsModel
import com.julia.apd.ping.Ping
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val hostsRepository: HostsRepository) : ViewModel() {
    private val _hosts = MutableLiveData<Resource<HostsModel>>()

    val hosts: LiveData<Resource<HostsModel>> = _hosts
    val latency = MutableLiveData<Pair<Int, Float>>()

    fun getHosts() {
        _hosts.value = Resource.loading()
        viewModelScope.launch {
            _hosts.value = hostsRepository.getHosts().value
        }
    }

    fun ping(hosts: List<HostModel>) {
        hosts.mapIndexed { index, value ->
            val ping = Ping()
            viewModelScope.launch {
                latency.value =
                    withContext(Dispatchers.IO) { Pair(index, ping.pingLatency(value.url)) }
            }
        }
    }
}