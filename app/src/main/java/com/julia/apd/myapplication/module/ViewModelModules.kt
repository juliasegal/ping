package com.julia.apd.myapplication.module


import com.julia.apd.myapplication.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { MainViewModel(hostsRepository = get()) }
}
