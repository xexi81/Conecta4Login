package com.xexi.conecta4Login.data.games

data class Games(
    val uid1: String = " ",
    val uid2: String = " ",
    var turn: Int = 0,
    val lastPlayed: Int? = null,
    val turnPlayer: Int = 0,
    val board: List<Int> = listOf(0),
    val name1: String= " ",
    val name2: String = " ",
    val photo1: String? = " ",
    val photo2: String? = " ",
    val id: String = " ",
    val winner: Int? = null,
    val finishSeen1: Boolean = false,
    val finishSeen2: Boolean = false
)