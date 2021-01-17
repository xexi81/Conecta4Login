package com.xexi.conecta4Login.data.login

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.xexi.conecta4Login.R
import com.xexi.conecta4Login.base.Resource
import kotlinx.coroutines.tasks.await
import java.util.*

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


    //Reset user by email
    override suspend fun resetUser(email: String): Resource<Boolean> {
        mAuth.setLanguageCode(Locale.getDefault().language) // Language user
        try {
            mAuth.sendPasswordResetEmail(email).await()
            return Resource.Success(true)
        } catch (e: Exception) {
            return Resource.Success(false)
        }
    }


    // Login an user on Google
    override suspend fun firebaseAuthWithGoogle(account: GoogleSignInAccount): Resource<Boolean> {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        try {
            mAuth.signInWithCredential(credential).await()
            return Resource.Success(true)
        } catch (e: Exception) {
        return Resource.Success(false)
        }
    }



}
