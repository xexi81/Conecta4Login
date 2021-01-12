package com.xexi.conecta4Login.domain.login

import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.login.User

interface IGetUserLogin {
    suspend fun getUserLogged(): Resource<User>
}