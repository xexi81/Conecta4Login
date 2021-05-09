package com.xexi.conecta4Login.data.login


import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.xexi.conecta4Login.base.Resource
import kotlinx.coroutines.tasks.await
import java.util.*

class RepoImplGetUserLogged: IRepoGetUserLogged {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance();

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
        return try {
            mAuth.signInWithCredential(credential).await()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Success(false)
        }
    }

}


fun getUserUid(): String? {
    val mAuth: FirebaseAuth = FirebaseAuth.getInstance();

    return mAuth.currentUser?.uid
}
