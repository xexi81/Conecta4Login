package com.xexi.conecta4Login.domain.login

import android.app.Activity
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.login.User

interface IGetUserLogin {
    suspend fun getUserLogged(): Resource<User>
    suspend fun registerUser(email: String, password: String): Resource<Boolean>
    suspend fun loginUser(email: String, password: String): Resource<Boolean>
    suspend fun firebaseAuthWithGoogle (account: GoogleSignInAccount): Resource<Boolean>
    suspend fun resetUser(email: String): Resource<Boolean>
}