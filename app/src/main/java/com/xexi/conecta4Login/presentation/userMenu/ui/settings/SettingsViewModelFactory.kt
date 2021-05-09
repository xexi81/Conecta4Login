package com.xexi.conecta4Login.presentation.userMenu.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xexi.conecta4Login.domain.userMenu.IFirestoreUsers

class SettingsViewModelFactory(private val userCase: IFirestoreUsers): ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IFirestoreUsers::class.java).newInstance(userCase)
    }

}
