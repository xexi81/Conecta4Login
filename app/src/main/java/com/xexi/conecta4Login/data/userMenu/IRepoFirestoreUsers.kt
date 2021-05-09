package com.xexi.conecta4Login.data.userMenu

import com.xexi.conecta4Login.base.Resource

interface IRepoFirestoreUsers {
    suspend fun getFirestoreUser(): Resource<FirestoreUser>
    suspend fun updateFirestoreUser(username: String, notifications: Boolean): Resource<Boolean>
}