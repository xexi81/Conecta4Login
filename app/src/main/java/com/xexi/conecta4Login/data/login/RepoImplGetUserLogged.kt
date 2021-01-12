package com.xexi.conecta4Login.data.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.xexi.conecta4Login.base.Resource

class RepoImplGetUserLogged: IRepoGetUserLogged {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance();
    override suspend fun getUserLogged(): Resource<User> {

        var user = User("hola", "que haces", "que dices")

        Log.d("Sergio", "el user en repo es ${user.email}")

        return Resource.Success(user)
/*
        var currentUser = mAuth.currentUser
        if (currentUser == null) {
            var user = User("", "", "")
            return Resource.Success(user)
        }
        else {
            var name: String = currentUser.displayName!!
            var email: String = currentUser.email!!
            var uid = currentUser.uid

            var user = User(name, email, uid)
            return Resource.Success(user)
        }
     */

    }
}