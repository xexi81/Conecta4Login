package com.xexi.conecta4Login.data.login

import com.xexi.conecta4Login.base.Resource

interface IRepoGetUserLogged {
    suspend fun getUserLogged(): Resource<User>
    suspend fun registerUser(email: String, password: String): Resource<Boolean>
    suspend fun loginUser(email: String, password: String): Resource<Boolean>
    suspend fun loginGoogleUser(email: String, password: String): Resource<Boolean>
}