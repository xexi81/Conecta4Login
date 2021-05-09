package com.xexi.conecta4Login.presentation.login.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.base.obtenerUsuario
import com.xexi.conecta4Login.data.login.User
import com.xexi.conecta4Login.domain.login.IGetUserLogin
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(useCase:IGetUserLogin): ViewModel() {

    private val _user = MutableLiveData<User>().apply {
        value = obtenerUsuario()
    }
    val user: LiveData<User> = _user

    val regUser = fun(email: String, password: String): LiveData<Resource<Boolean>> {
        val registeredUser = liveData(Dispatchers.IO) {
            emit(Resource.Loading())

            try {
                val alreadyRegistered: Resource<Boolean> = useCase.registerUser(email, password)
                // cuando tengo datos para enviar a la activity
                emit(alreadyRegistered)
            } catch (e: Exception) {
                // cuando algo ha fallado
                emit(Resource.Failure(e))
                Log.e("ERROR", e.message.toString())
            }
        }

        return registeredUser
    }

    val logUser = fun(email: String, password: String): LiveData<Resource<Boolean>> {
        val loginUser = liveData(Dispatchers.IO) {
            emit(Resource.Loading())

            try {
                val alreadyRegistered: Resource<Boolean> = useCase.loginUser(email, password)
                // cuando tengo datos para enviar a la activity
                emit(alreadyRegistered)
            } catch (e: Exception) {
                // cuando algo ha fallado
                emit(Resource.Failure(e))
                Log.e("ERROR", e.message.toString())
            }
        }
        return loginUser
    }

    val rememberUser = fun(email: String): LiveData<Resource<Boolean>> {
        val resetUser = liveData(Dispatchers.IO) {
            emit(Resource.Loading())

            try {
                val resetUserLog: Resource<Boolean> = useCase.resetUser(email)
                // cuando tengo datos para enviar a la activity
                emit(resetUserLog)
            } catch (e: Exception) {
                // cuando algo ha fallado
                emit(Resource.Failure(e))
                Log.e("ERROR", e.message.toString())
            }
        }
        return resetUser
    }


    val loginWithGoogle = fun(account: GoogleSignInAccount): LiveData<Resource<Boolean>> {
        val loginGoogleUser = liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val alreadyRegistered: Resource<Boolean> = useCase.firebaseAuthWithGoogle(account)
                // cuando tengo datos para enviar a la activity
                emit(alreadyRegistered)
            } catch (e: Exception) {
                // cuando algo ha fallado
                emit(Resource.Failure(e))
                Log.e("ERROR", e.message.toString())
            }
        }
        return loginGoogleUser
    }

}