package com.xexi.conecta4Login.presentation.userMenu.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.games.Games
import com.xexi.conecta4Login.data.userMenu.FirestoreUser
import com.xexi.conecta4Login.data.userMenu.IRepoFirestoreUsers
import com.xexi.conecta4Login.domain.games.IGetGamesUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import kotlinx.coroutines.flow.collect

class HomeViewModel(useCase: IGetGamesUser) : ViewModel() {


    val gamesList = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            useCase.getGamesUser().collect {gameList ->
                emit(gameList)
            }
        } catch (e: Exception) {
            emit(Resource.Failure(e))
            Log.e("ERROR", e.message.toString())
        }
    }

    val gamesList2 = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            useCase.getGamesUser2().collect {gameList ->
                emit(gameList)
            }
        } catch (e: Exception) {
            emit(Resource.Failure(e))
            Log.e("ERROR", e.message.toString())
        }
    }


    val gameData = fun(id: String) =
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())

            try {
                val gameData = useCase.gameData(id).collect { game ->
                    emit(game)
                }
            } catch (e: Exception) {
                emit(Resource.Failure(e))
                Log.e("ERROR", e.message.toString())
            }
        }


    val moveTile = fun(id: String, position: Int) =
        liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            emit (useCase.moveTile(id, position))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
            Log.e("ERROR", e.message.toString())
        }
    }


    // new Game
    val newGame  = liveData(Dispatchers.IO) {
        Log.d("Sergio", "antes del loading de newgame")
        emit(Resource.Loading())

        try {
            val gamesList: Resource<Boolean> = useCase.newGame()
            Log.d("Sergio", "transmitimos juegos")
            // cuando tengo datos para enviar a la activity
            emit(gamesList)
        } catch (e: Exception) {
            // cuando algo ha fallado
            emit(Resource.Failure(e))
            Log.e("Sergio", e.message.toString())
        }
    }



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

}