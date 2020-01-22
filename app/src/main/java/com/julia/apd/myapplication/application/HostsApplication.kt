package com.julia.apd.myapplication.application

import android.app.Application
import com.julia.apd.myapplication.module.hostsServerModules
import com.julia.apd.myapplication.module.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class HostsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@HostsApplication)
            modules(
                listOf(
                    hostsServerModules,
                    viewModelModules
                )
            )
        }
    }
}