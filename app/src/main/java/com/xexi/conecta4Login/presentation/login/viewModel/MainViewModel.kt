package com.xexi.conecta4Login.presentation.login.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.domain.login.IGetUserLogin
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(useCase:IGetUserLogin): ViewModel() {

    val fetchVersionCode = liveData(Dispatchers.IO) {
        // mientras no recibo nada, estoy en loading
        emit(Resource.Loading())

        try {
            val user = useCase.getUserLogged()
            // cuando tengo datos para enviar a la activity
            emit(Resource.Success(user))
        } catch (e: Exception) {
            // cuando algo ha fallado
            emit(Resource.Failure(e))
            Log.e("ERROR", e.message.toString())
        }
    }
}