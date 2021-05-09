package com.xexi.conecta4Login.domain.userMenu

import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.userMenu.FirestoreUser
import com.xexi.conecta4Login.data.userMenu.IRepoFirestoreUsers

class FirestoreUsersImpl(private val repo: IRepoFirestoreUsers): IFirestoreUsers {
    override suspend fun getFirestoreUser(): Resource<FirestoreUser> = repo.getFirestoreUser()
    override suspend fun updateFirestoreUser(username: String, notifications: Boolean): Resource<Boolean> = repo.updateFirestoreUser(username, notifications)
}