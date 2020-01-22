package com.julia.apd.myapplication.module

import com.julia.apd.myapplication.dao.HostsRepository
import com.julia.apd.myapplication.dao.HostsRepositoryImpl
import com.julia.apd.myapplication.networking.HttpClient
import com.julia.apd.myapplication.networking.ResponseHandler
import com.julia.apd.myapplication.networking.RetrofitFactory
import com.julia.apd.myapplication.remote.HostsApi
import org.koin.dsl.module

val hostsServerModules = module {
    single { HttpClient.createHttpClient() }
    single { ResponseHandler() }
    single { RetrofitFactory.create(HostsApi::class.java, HostsApi.url, okHttpClient = get()) }
    single<HostsRepository> {
        HostsRepositoryImpl(
            hostsApi = get(),
            responseHandler = get()
        )
    }
}



