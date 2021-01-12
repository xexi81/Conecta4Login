package com.xexi.conecta4Login.domain.login

import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.login.IRepoGetUserLogged
import com.xexi.conecta4Login.data.login.User

class GetUserLoginImpl(private val repo: IRepoGetUserLogged): IGetUserLogin {
        override suspend fun getUserLogged(): Resource<User> = repo.getUserLogged()
}