package com.xexi.conecta4Login.presentation.userMenu.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.base.obtenerUsuario
import com.xexi.conecta4Login.data.login.User
import com.xexi.conecta4Login.domain.userMenu.IUserMenu
import kotlinx.coroutines.Dispatchers
import java.lang.Exception


class UserMenuViewModel(useCase: IUserMenu) : ViewModel() {

    private val _user = MutableLiveData<User>().apply {
        value = obtenerUsuario()
    }
    val user: LiveData<User> = _user



    val updateUser = fun(user: User): LiveData<Resource<Boolean>> {
        val updateUI = liveData(Dispatchers.IO) {
            emit(Resource.Loading())

            try {
                val alreadyUpdated: Resource<Boolean> = useCase.updateUI(user)
                // cuando tengo datos para enviar a la activity
                emit(alreadyUpdated)
            } catch (e: Exception) {
                // cuando algo ha fallado
                emit(Resource.Failure(e))
                Log.e("ERROR", e.message.toString())
            }
        }

        return updateUI
    }



}

