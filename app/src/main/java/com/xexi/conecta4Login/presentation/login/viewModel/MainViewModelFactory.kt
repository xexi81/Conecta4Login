package com.xexi.conecta4Login.presentation.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xexi.conecta4Login.domain.login.IGetUserLogin

class MainViewModelFactory(private val userCase: IGetUserLogin): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IGetUserLogin::class.java).newInstance(userCase)
    }

}