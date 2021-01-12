package com.xexi.conecta4Login.data.login

import com.xexi.conecta4Login.base.Resource

interface IRepoGetUserLogged {
    suspend fun getUserLogged(): Resource<User>
}