package com.xexi.conecta4Login.domain.login

import android.app.Activity
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.login.IRepoGetUserLogged
import com.xexi.conecta4Login.data.login.User
import kotlinx.coroutines.flow.Flow

class GetUserLoginImpl(private val repo: IRepoGetUserLogged): IGetUserLogin {
        override suspend fun registerUser(email: String, password: String): Resource<Boolean> = repo.registerUser(email, password)
        override suspend fun loginUser(email: String, password: String): Resource<Boolean> = repo.loginUser(email, password)
        override suspend fun firebaseAuthWithGoogle(account: GoogleSignInAccount): Resource<Boolean> = repo.firebaseAuthWithGoogle(account)
        override suspend fun resetUser(email: String): Resource<Boolean> = repo.resetUser(email)
}