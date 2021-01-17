package com.xexi.conecta4Login.domain.login

import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.login.IRepoGetUserLogged
import com.xexi.conecta4Login.data.login.User

class GetUserLoginImpl(private val repo: IRepoGetUserLogged): IGetUserLogin {
        override suspend fun getUserLogged(): Resource<User> = repo.getUserLogged()
        override suspend fun registerUser(email: String, password: String): Resource<Boolean> = repo.registerUser(email, password)
        override suspend fun loginUser(email: String, password: String): Resource<Boolean> = repo.loginUser(email, password)
        override suspend fun loginGoogleUser(email: String, password: String): Resource<Boolean> = repo.loginGoogleUser(email, password)
}