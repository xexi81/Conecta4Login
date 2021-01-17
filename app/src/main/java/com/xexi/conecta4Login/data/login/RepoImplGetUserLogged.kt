package com.xexi.conecta4Login.data.login


import com.google.firebase.auth.FirebaseAuth
import com.xexi.conecta4Login.base.Resource
import kotlinx.coroutines.tasks.await

class RepoImplGetUserLogged: IRepoGetUserLogged {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance();

    // Obtain user
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


    // Register an user on firebase
    override suspend fun registerUser(email: String, password: String): Resource<Boolean> {
        // Firebase create user with email and pass
        try {
            mAuth.createUserWithEmailAndPassword(email, password).await()
            return Resource.Success(true)
        } catch (e: Exception) {
            return Resource.Success(false)
        }
    }


    // Login an user on firebase
    override suspend fun loginUser(email: String, password: String): Resource<Boolean> {
        // Firebase create user with email and pass
        try {
            mAuth.signInWithEmailAndPassword(email, password).await()
            return Resource.Success(true)
        } catch (e: Exception) {
            return Resource.Success(false)
        }
    }


    // Login an user on Google
    override suspend fun loginGoogleUser(email: String, password: String): Resource<Boolean> {

        // Firebase create user with email and pass
        val result = mAuth.signInWithEmailAndPassword(email, password)

        // Waiting until result is complete
        while(!result.isComplete) {
        }

        return if (result.isSuccessful) {
            Resource.Success(true)
        } else Resource.Success(false)

    }

}
