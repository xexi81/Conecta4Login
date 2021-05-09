package com.xexi.conecta4Login.data.userMenu

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.xexi.conecta4Login.base.Resource
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class RepoImplFirestoreUsers: IRepoFirestoreUsers {
    override suspend fun getFirestoreUser(): Resource<FirestoreUser> {
        val currentUser = FirebaseAuth.getInstance().currentUser

        val resultData = FirebaseFirestore.getInstance()
                .collection("User")
                .document(currentUser?.uid!!)
                .get()
                .await()

        if (!resultData.getString("email").isNullOrEmpty()) {
            return Resource.Success(resultData.toObject(FirestoreUser::class.java)!!)
        } else {
            return Resource.Failure(throw NullPointerException("FirestoreUser empty"))
        }

    }

    override suspend fun updateFirestoreUser(username: String, notifications: Boolean): Resource<Boolean> {
        val currentUser = FirebaseAuth.getInstance().currentUser

        val map = mutableMapOf<String, Any>()
        map ["username"] = username
        map ["notifications"] = notifications


        return try {
            val resultData = FirebaseFirestore.getInstance()
                    .collection("User")
                    .document(currentUser?.uid!!)
                    .update(map)
                    .await()

            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Failure(e)
        }


    }


}

suspend fun getFirestoreUser(): FirestoreUser? {
    val currentUser = FirebaseAuth.getInstance().currentUser

    val resultData = FirebaseFirestore.getInstance()
        .collection("User")
        .document(currentUser?.uid!!)
        .get()
        .await()

    return resultData.toObject(FirestoreUser::class.java)
}


suspend fun updateVictoriesUser(win: Boolean, uid: String) {
    val resultData = FirebaseFirestore.getInstance()
        .collection("User")
        .document(uid)
        .get()
        .await()

    val user = resultData.toObject(FirestoreUser::class.java)
    val wins = user!!.wins + 1
    val looses = user!!.looses + 1


    if (win) {
        val resultData = FirebaseFirestore.getInstance()
            .collection("User")
            .document(uid)
            .update("wins", wins)
    } else {
        val resultData = FirebaseFirestore.getInstance()
            .collection("User")
            .document(uid)
            .update("wins", wins)
    }


}