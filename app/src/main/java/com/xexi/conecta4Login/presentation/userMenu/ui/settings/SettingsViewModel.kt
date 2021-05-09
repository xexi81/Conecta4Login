package com.xexi.conecta4Login.presentation.userMenu.ui.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.userMenu.FirestoreUser
import com.xexi.conecta4Login.domain.userMenu.IFirestoreUsers
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class SettingsViewModel(useCase: IFirestoreUsers) : ViewModel() {


    val firestoreUser = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            val firestoreUser: Resource<FirestoreUser> = useCase.getFirestoreUser()
            // cuando tengo datos para enviar a la activity
            emit(firestoreUser)
        } catch (e: Exception) {
            // cuando algo ha fallado
            emit(Resource.Failure(e))
            Log.e("Sergio", e.message.toString())
        }
    }

    val updateFirestoreUser = fun(username: String, notifications: Boolean) =
            liveData(Dispatchers.IO) {
                emit(Resource.Loading())
                try {
                    val updateUser: Resource<Boolean> = useCase.updateFirestoreUser(username, notifications)
                    // cuando tengo datos para enviar a la activity
                    emit(updateUser)
                } catch (e: Exception) {
                    // cuando algo ha fallado
                    emit(Resource.Failure(e))
                    Log.e("ERROR", e.message.toString())
                }
            }

}