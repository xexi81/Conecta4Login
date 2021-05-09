package com.xexi.conecta4Login.domain.userMenu

import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.userMenu.FirestoreUser

interface IFirestoreUsers {
    suspend fun getFirestoreUser(): Resource<FirestoreUser>
    suspend fun updateFirestoreUser(username: String, notifications: Boolean): Resource<Boolean>
}