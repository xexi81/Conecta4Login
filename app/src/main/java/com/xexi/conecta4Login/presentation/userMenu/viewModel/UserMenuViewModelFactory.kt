package com.xexi.conecta4Login.presentation.userMenu.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xexi.conecta4Login.domain.userMenu.IUserMenu

class UserMenuViewModelFactory(private val userCase: IUserMenu): ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IUserMenu::class.java).newInstance(userCase)
    }

}
