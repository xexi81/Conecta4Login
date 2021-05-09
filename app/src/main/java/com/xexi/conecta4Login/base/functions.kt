package com.xexi.conecta4Login.base

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.xexi.conecta4Login.data.login.User

fun obtenerUsuario(): User {
    val currentUser = FirebaseAuth.getInstance().currentUser
    var name: String? = null
    var email: String? = null
    var uid: String? = null
    var photo: Uri? = null

    if (currentUser?.displayName!=null) {
         name = currentUser.displayName
    }

    if (currentUser?.email!=null) {
        email = currentUser.email
    }

    if (currentUser?.uid!=null) {
        uid = currentUser.uid
    }

    if (currentUser?.photoUrl!=null) {
        photo = currentUser.photoUrl
    }

    return User(name?:"", email?:"", uid?:"", photo)

}