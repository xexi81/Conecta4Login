package com.xexi.conecta4Login.data.userMenu


data class FirestoreUser(
        val email: String = "",
        val connectionDate: String = "",
        val guild: String? = "",
        val notifications: Boolean = true,
        val wins: Int = 0,
        val looses: Int = 0,
        val totalMatches: Int = 0,
        val username: String? = null,
        val photo: String? = null)