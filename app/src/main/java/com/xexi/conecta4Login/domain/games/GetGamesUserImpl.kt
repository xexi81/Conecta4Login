package com.xexi.conecta4Login.domain.games

import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.games.Games
import com.xexi.conecta4Login.data.games.IRepoGetGamesUser
import com.xexi.conecta4Login.data.userMenu.FirestoreUser
import kotlinx.coroutines.flow.Flow


class GetGamesUserImpl(private val repository: IRepoGetGamesUser): IGetGamesUser {
    override suspend fun getGamesUser(): Flow<Resource<List<Games>>> = repository.getGamesUser()
    override suspend fun getGamesUser2(): Flow<Resource<List<Games>>> = repository.getGamesUser2()
    override suspend fun newGame(): Resource<Boolean> = repository.newGame()
    override suspend fun moveTile(id: String, position: Int): Resource<Boolean> = repository.moveTile(id, position)
    override suspend fun gameData(id: String): Flow<Resource<Games>> = repository.gameData(id)
    override suspend fun getFirestoreUser(): Resource<FirestoreUser> = repository.getFirestoreUser()
}