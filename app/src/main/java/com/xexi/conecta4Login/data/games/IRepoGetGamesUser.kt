package com.xexi.conecta4Login.data.games

import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.userMenu.FirestoreUser
import kotlinx.coroutines.flow.Flow

interface IRepoGetGamesUser {
    suspend fun getGamesUser(): Flow<Resource<List<Games>>>
    suspend fun getGamesUser2(): Flow<Resource<List<Games>>>
    suspend fun newGame(): Resource<Boolean>
    suspend fun gameData(id: String): Flow<Resource<Games>>
    suspend fun moveTile(id: String, position: Int): Resource<Boolean>
    suspend fun getFirestoreUser(): Resource<FirestoreUser>
}