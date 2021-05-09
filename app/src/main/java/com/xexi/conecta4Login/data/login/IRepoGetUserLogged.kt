package com.xexi.conecta4Login.data.login

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.xexi.conecta4Login.base.Resource


interface IRepoGetUserLogged {
    suspend fun registerUser(email: String, password: String): Resource<Boolean>
    suspend fun loginUser(email: String, password: String): Resource<Boolean>
    suspend fun firebaseAuthWithGoogle(account: GoogleSignInAccount): Resource<Boolean>
    suspend fun resetUser(email: String): Resource<Boolean>
}