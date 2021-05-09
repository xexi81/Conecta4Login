package com.xexi.conecta4Login.domain.userMenu

import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.login.User

interface IUserMenu {
        suspend fun updateUI(user: User): Resource<Boolean>
}