package com.xexi.conecta4Login.data.userMenu

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.login.User
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime

class RepoImplUserMenu: IRepoUserMenu {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun updateUI(user: User): Resource<Boolean> {

        val resultData = FirebaseFirestore.getInstance()
                .collection("User")
                .document(user.uid)
                .get()
                .await()

        if (resultData.data.isNullOrEmpty()) {
            val currentDate = LocalDateTime.now().toString()
            val firestoreUser = FirestoreUser(user.email, currentDate, null, photo = user.photo.toString())
            val insertData = FirebaseFirestore.getInstance()
                .collection("User")
                .document(user.uid)
                .set(firestoreUser)
                .await()
        } else  {
            val currentDate = LocalDateTime.now().toString()
            val insertData = FirebaseFirestore.getInstance()
                .collection("User")
                .document(user.uid)
                .update("connectionDate", currentDate)
                .await()
        }

            return Resource.Success(true)

    }
}