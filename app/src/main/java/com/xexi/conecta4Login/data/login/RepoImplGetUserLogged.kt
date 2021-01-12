package com.xexi.conecta4Login.data.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.xexi.conecta4Login.base.Resource

class RepoImplGetUserLogged: IRepoGetUserLogged {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance();
    override suspend fun getUserLogged(): Resource<User> {

        val currentUser = mAuth.currentUser
        if (currentUser == null) {
            val user = User("", "", "")
            return Resource.Success(user)
        }
        else {
            val name: String = currentUser.displayName!!
            val email: String = currentUser.email!!
            val uid = currentUser.uid

            val user = User(name, email, uid)
            return Resource.Success(user)
        }

    }
}