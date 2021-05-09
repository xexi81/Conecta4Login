package com.xexi.conecta4Login.data.userMenu

import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.login.User

interface IRepoUserMenu {
    suspend fun updateUI(user: User): Resource<Boolean>
}