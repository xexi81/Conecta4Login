package com.xexi.conecta4Login.presentation.userMenu.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xexi.conecta4Login.domain.games.GetGamesUserImpl
import com.xexi.conecta4Login.domain.games.IGetGamesUser


// Override ViewModelProvider.NewInstanceFactory to create the ViewModel (VM).
class HomeViewModelFactory(private val useCase:IGetGamesUser): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IGetGamesUser::class.java).newInstance(useCase)
    }
}