package com.xexi.conecta4Login.domain.userMenu

import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.login.User
import com.xexi.conecta4Login.data.userMenu.IRepoUserMenu


class UserMenuImpl(private val repo: IRepoUserMenu): IUserMenu {
    override suspend fun updateUI(user: User): Resource<Boolean> = repo.updateUI(user)
}