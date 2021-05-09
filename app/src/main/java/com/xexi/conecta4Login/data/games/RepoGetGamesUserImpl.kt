package com.xexi.conecta4Login.data.games

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.userMenu.FirestoreUser
import com.xexi.conecta4Login.data.userMenu.getFirestoreUser
import com.xexi.conecta4Login.data.userMenu.updateVictoriesUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class RepoGetGamesUserImpl: IRepoGetGamesUser {

    @ExperimentalCoroutinesApi
    override suspend fun getGamesUser(): Flow<Resource<List<Games>>> = callbackFlow {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val gamesList = mutableListOf<Games>()

        // Games with player1 = currentUser
        val snapshot = FirebaseFirestore.getInstance()
            .collection("Games")
            .whereEqualTo("uid1", currentUser?.uid)
            .whereEqualTo("finishSeen1", false)

        val subscription = snapshot.addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
            if (documentSnapshot != null) {
                gamesList.clear()
                for (document in documentSnapshot.documents) {
                    document.toObject(Games::class.java)?.let { gamesList.add(it) }
                }
                offer(Resource.Success(gamesList))
            } else channel.close(firebaseFirestoreException?.cause)
        }

        awaitClose{ subscription.remove() }
    }

    @ExperimentalCoroutinesApi
    override suspend fun getGamesUser2(): Flow<Resource<List<Games>>> = callbackFlow {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val gamesList = mutableListOf<Games>()

        // Games with player1 = currentUser
        val snapshot = FirebaseFirestore.getInstance()
            .collection("Games")
            .whereEqualTo("uid2", currentUser?.uid)
            .whereEqualTo("finishSeen2", false)

        val subscription = snapshot.addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
            if (documentSnapshot != null) {
                gamesList.clear()
                for (document in documentSnapshot.documents) {
                    document.toObject(Games::class.java)?.let { gamesList.add(it) }
                }
                offer(Resource.Success(gamesList))
            } else channel.close(firebaseFirestoreException?.cause)
        }

        awaitClose{ subscription.remove() }
    }

    @ExperimentalCoroutinesApi
    override suspend fun gameData(id: String): Flow<Resource<Games>> = callbackFlow {

        // Games with player1 = currentUser
        val snapshot = FirebaseFirestore.getInstance()
            .collection("Games")
            .document(id)

        val subscription = snapshot.addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
            if (documentSnapshot != null) {
                offer(Resource.Success(documentSnapshot.toObject(Games::class.java)!!))
            } else channel.close(firebaseFirestoreException?.cause)
        }
        awaitClose{ subscription.remove() }
    }

    override suspend fun moveTile(id: String, position: Int): Resource<Boolean> {
        val currentUser = FirebaseAuth.getInstance().currentUser

        val resultData = FirebaseFirestore.getInstance()
            .collection("Games")
            .document(id)
            .get()
            .await()

        if (!resultData.exists()) return Resource.Failure(throw RuntimeException("error interno - juego no encontrado"))

        val game = resultData.toObject(Games::class.java)
        val board = game!!.board.toMutableList()
        var turnPlayer = 0


        // Validation player turn
        if (game!!.turnPlayer == 1 && game.uid1 == currentUser!!.uid) {
            turnPlayer = 2
        }

        if (game!!.turnPlayer == 2 && game.uid2 == currentUser!!.uid) {
            turnPlayer = 1
        }

        if (turnPlayer == 0) {
            return Resource.Failure(throw RuntimeException("No es tu turno!!"))
        }

        // Validation position clicked
        if ((game.board[position]!=0)) {
            return Resource.Failure(throw RuntimeException("No puedes mover ahí, casilla $position"))
        } else {
            if (game.lastPlayed != null)  board[game.lastPlayed] = turnPlayer
        }


        if (position<0 || position > 63) {
            return Resource.Failure(throw RuntimeException("error interno - posición errónea"))
        }

        // Validamos que la posición inferior esté ocupada
        if (!evaluatePosition(position, game.board)) {
            if (!evaluateLastPlayer(position, game.lastPlayed?:0)) {
                return Resource.Failure(throw RuntimeException("casilla errónea, casilla $position"))
            }
        }


        val map = mutableMapOf<String, Any>()
        map ["turn"] = game.turn++
        map ["turnPlayer"] = turnPlayer
        map ["lastPlayed"] = position
        map ["board"] = board


        if (validateVictory(board)) {
            map["winner"]= game.turnPlayer

            if (game.turnPlayer == 1) {
                map["finishSeen1"] = true
                updateVictoriesUser(true, game.uid1)
                updateVictoriesUser(false, game.uid2)
            } else {
                map["finishSeen2"] = true
                updateVictoriesUser(true, game.uid2)
                updateVictoriesUser(false, game.uid1)
            }

        }

        val updateData = FirebaseFirestore.getInstance()
            .collection("Games")
            .document(id)
            .update(map)
            .await()



        return Resource.Success(true)
    }

    override suspend fun newGame(): Resource<Boolean> {
        val currentUser = FirebaseAuth.getInstance().currentUser

        // buscamos juegos ya iniciados pendientes de entrar usuario2
        val resultData = FirebaseFirestore.getInstance()
            .collection("Games")
            .whereEqualTo("uid2", " ")
            .get()
            .await()

        if (resultData.size() > 0) {
            Log.d("Sergio", "encontramos juegos creados")
            for (document in resultData.documents) {
                val game = document.toObject(Games::class.java)!!
                // si existe alguno donde el usuario de creación no es nuestro usuario, ya tenemos usuario2
                if (game.uid1 != currentUser?.uid) {
                    insertPlayer2Game(document.id)
                    return Resource.Success(true)
                }
            }
        }

        // sino, creamos juego
        Log.d("Sergio", "vamos a crear juego")
        newGameUser()
        return Resource.Success(true)
    }

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
}

suspend fun insertPlayer2Game(id: String) {
    // Current User
    val currentUser = FirebaseAuth.getInstance().currentUser

    // UserFirestoreNameUser
    val user = getFirestoreUser()

    var username: String? = null
    username = if (user?.username.isNullOrEmpty()) {
        currentUser?.email
    } else user?.username


    val map = mutableMapOf<String, Any>()
    map ["uid2"] = currentUser?.uid!!
    map ["turn"] = 1
    map ["turnPlayer"] = 1
    map ["name2"] = username!!
    map ["photo2"] = currentUser.photoUrl.toString()
    map ["id"] = id



    val resultData = FirebaseFirestore.getInstance()
        .collection("Games")
        .document(id)
        .update(map)
        .await()
}

suspend fun newGameUser() {
    // Current User
    val currentUser = FirebaseAuth.getInstance().currentUser

    // UserFirestoreNameUser
    val user = getFirestoreUser()

    var username: String? = null
    username = if (user?.username.isNullOrEmpty()) {
        currentUser?.email
    } else user?.username

    val boardList = listOf<Int>(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)

    val game = Games(uid1 = currentUser!!.uid, photo1 = currentUser.photoUrl.toString(), name1 = username!!, board = boardList)

    // vamos a insertar juego
    Log.d("Sergio", "insertamos juego")
    val resultData = FirebaseFirestore.getInstance()
        .collection("Games")
        .add(game)
}

fun evaluateLastPlayer(position: Int, lastPlayer: Int): Boolean {
    when(position) {
        8 -> return lastPlayer == 0
        9 -> return lastPlayer == 1
        10 -> return lastPlayer == 2
        11 -> return lastPlayer == 3
        12 -> return lastPlayer == 4
        13 -> return lastPlayer == 5
        14 -> return lastPlayer == 6
        15 -> return lastPlayer == 7
        16 -> return lastPlayer == 8
        17 -> return lastPlayer == 9
        18 -> return lastPlayer == 10
        19 -> return lastPlayer == 11
        20 -> return lastPlayer == 12
        21 -> return lastPlayer == 13
        22 -> return lastPlayer == 14
        23 -> return lastPlayer == 15
        24 -> return lastPlayer == 16
        25 -> return lastPlayer == 17
        26 -> return lastPlayer == 18
        27 -> return lastPlayer == 19
        28 -> return lastPlayer == 20
        29 -> return lastPlayer == 21
        30 -> return lastPlayer == 22
        31 -> return lastPlayer == 23
        32 -> return lastPlayer == 24
        33 -> return lastPlayer == 25
        34 -> return lastPlayer == 26
        35 -> return lastPlayer == 27
        36 -> return lastPlayer == 28
        37 -> return lastPlayer == 29
        38 -> return lastPlayer == 30
        39 -> return lastPlayer == 31
        40 -> return lastPlayer == 32
        41 -> return lastPlayer == 33
        42 -> return lastPlayer == 34
        43 -> return lastPlayer == 35
        44 -> return lastPlayer == 36
        45 -> return lastPlayer == 37
        46 -> return lastPlayer == 38
        47 -> return lastPlayer == 39
        48 -> return lastPlayer == 40
        49 -> return lastPlayer == 41
        50 -> return lastPlayer == 42
        51 -> return lastPlayer == 43
        52 -> return lastPlayer == 44
        53 -> return lastPlayer == 45
        54 -> return lastPlayer == 46
        55 -> return lastPlayer == 47
        56 -> return lastPlayer == 48
        57 -> return lastPlayer == 49
        58 -> return lastPlayer == 50
        59 -> return lastPlayer == 51
        60 -> return lastPlayer == 52
        61 -> return lastPlayer == 53
        62 -> return lastPlayer == 54
        63 -> return lastPlayer == 55
        else -> return false
    }
}

fun evaluatePosition(position: Int, board: List<Int>): Boolean {
    when(position) {
        0 -> return true
        1 -> return true
        2 -> return true
        3 -> return true
        4 -> return true
        5 -> return true
        6 -> return true
        7 -> return true
        8 -> return board[0] != 0
        9 -> return board[1] != 0
        10 -> return board[2] != 0
        11 -> return board[3] != 0
        12 -> return board[4] != 0
        13 -> return board[5] != 0
        14 -> return board[6] != 0
        15 -> return board[7] != 0
        16 -> return board[8] != 0
        17 -> return board[9] != 0
        18 -> return board[10] != 0
        19 -> return board[11] != 0
        20 -> return board[12] != 0
        21 -> return board[13] != 0
        22 -> return board[14] != 0
        23 -> return board[15] != 0
        24 -> return board[16] != 0
        25 -> return board[17] != 0
        26 -> return board[18] != 0
        27 -> return board[19] != 0
        28 -> return board[20] != 0
        29 -> return board[21] != 0
        30 -> return board[22] != 0
        31 -> return board[23] != 0
        32 -> return board[24] != 0
        33 -> return board[25] != 0
        34 -> return board[26] != 0
        35 -> return board[27] != 0
        36 -> return board[28] != 0
        37 -> return board[29] != 0
        38 -> return board[30] != 0
        39 -> return board[31] != 0
        40 -> return board[32] != 0
        41 -> return board[33] != 0
        42 -> return board[34] != 0
        43 -> return board[35] != 0
        44 -> return board[36] != 0
        45 -> return board[37] != 0
        46 -> return board[38] != 0
        47 -> return board[39] != 0
        48 -> return board[40] != 0
        49 -> return board[41] != 0
        50 -> return board[42] != 0
        51 -> return board[43] != 0
        52 -> return board[44] != 0
        53 -> return board[45] != 0
        54 -> return board[46] != 0
        55 -> return board[47] != 0
        56 -> return board[48] != 0
        57 -> return board[49] != 0
        58 -> return board[50] != 0
        59 -> return board[51] != 0
        60 -> return board[52] != 0
        61 -> return board[53] != 0
        62 -> return board[54] != 0
        63 -> return board[55] != 0
        else -> return false
    }
}

fun validateVictory(board: List<Int>): Boolean {


    // hor. file1
    if (board[0] == board[1] && board[0] == board[2] && board[0] == board[3] && board[0]!=0) return true
    if (board[1] == board[2] && board[1] == board[3] && board[1] == board[4] && board[1]!=0) return true
    if (board[2] == board[3] && board[2] == board[4] && board[2] == board[5] && board[2]!=0) return true
    if (board[3] == board[4] && board[3] == board[5] && board[3] == board[6] && board[3]!=0) return true
    if (board[4] == board[5] && board[4] == board[6] && board[4] == board[7] && board[4]!=0) return true

    // horizontal file 2
    if (board[8]  == board[9]  && board[8]  == board[10] && board[8] == board[11] && board[8]!=0) return true
    if (board[9]  == board[10] && board[9]  == board[11] && board[9] == board[12] && board[9]!=0) return true
    if (board[10] == board[11] && board[10] == board[12] && board[10] == board[13] && board[10]!=0) return true
    if (board[11] == board[12] && board[11] == board[13] && board[11] == board[14] && board[11]!=0) return true
    if (board[12] == board[13] && board[12] == board[14] && board[12] == board[15] && board[12]!=0) return true

    // horizontal file 3
    if (board[16] == board[17] && board[16] == board[18] && board[16] == board[19] && board[16]!=0) return true
    if (board[17] == board[18] && board[17] == board[19] && board[17] == board[20] && board[17]!=0) return true
    if (board[18] == board[19] && board[18] == board[20] && board[18] == board[21] && board[18]!=0) return true
    if (board[19] == board[20] && board[19] == board[21] && board[19] == board[22] && board[19]!=0) return true
    if (board[20] == board[21] && board[20] == board[22] && board[20] == board[23] && board[20]!=0) return true

    // horizontal file 4
    if (board[24] == board[25] && board[24] == board[26] && board[24] == board[27] && board[24]!=0) return true
    if (board[25] == board[26] && board[25] == board[27] && board[25] == board[28] && board[25]!=0) return true
    if (board[26] == board[27] && board[26] == board[28] && board[26] == board[29] && board[26]!=0) return true
    if (board[27] == board[28] && board[27] == board[29] && board[27] == board[30] && board[27]!=0) return true
    if (board[28] == board[29] && board[28] == board[30] && board[28] == board[31] && board[28]!=0) return true

    // horizontal file 5
    if (board[32] == board[33] && board[32] == board[34] && board[32] == board[35] && board[32]!=0) return true
    if (board[33] == board[34] && board[33] == board[35] && board[33] == board[36] && board[33]!=0) return true
    if (board[34] == board[35] && board[34] == board[36] && board[34] == board[37] && board[34]!=0) return true
    if (board[35] == board[36] && board[35] == board[37] && board[35] == board[38] && board[35]!=0) return true
    if (board[36] == board[37] && board[36] == board[38] && board[36] == board[39] && board[36]!=0) return true

    // horizontal file 6
    if (board[40] == board[41] && board[40] == board[42] && board[40] == board[43] && board[40]!=0) return true
    if (board[41] == board[42] && board[41] == board[43] && board[41] == board[44] && board[41]!=0) return true
    if (board[42] == board[43] && board[42] == board[44] && board[42] == board[45] && board[42]!=0) return true
    if (board[43] == board[44] && board[43] == board[45] && board[43] == board[46] && board[43]!=0) return true
    if (board[44] == board[45] && board[44] == board[46] && board[44] == board[47] && board[44]!=0) return true

    // horizontal file 7
    if (board[48] == board[49] && board[48] == board[50] && board[48] == board[51] && board[48]!=0) return true
    if (board[49] == board[50] && board[49] == board[51] && board[49] == board[52] && board[49]!=0) return true
    if (board[50] == board[51] && board[50] == board[52] && board[50] == board[53] && board[50]!=0) return true
    if (board[51] == board[52] && board[51] == board[53] && board[51] == board[54] && board[51]!=0) return true
    if (board[52] == board[53] && board[52] == board[54] && board[52] == board[55] && board[52]!=0) return true

    // horizontal file 8
    if (board[56] == board[57] && board[56] == board[58] && board[56] == board[59] && board[56]!=0) return true
    if (board[57] == board[58] && board[57] == board[59] && board[57] == board[60] && board[57]!=0) return true
    if (board[58] == board[59] && board[58] == board[60] && board[58] == board[61] && board[58]!=0) return true
    if (board[59] == board[60] && board[59] == board[61] && board[59] == board[62] && board[59]!=0) return true
    if (board[60] == board[61] && board[60] == board[62] && board[60] == board[63] && board[60]!=0) return true

    // vertical column1
    if ( board[0] == board[8]  &&  board[0] == board[16] &&  board[0] == board[24] &&  board[0]!=0) return true
    if ( board[8] == board[16] &&  board[8] == board[24] &&  board[8] == board[32] &&  board[8]!=0) return true
    if (board[16] == board[24] && board[16] == board[32] && board[16] == board[40] && board[16]!=0) return true
    if (board[24] == board[32] && board[24] == board[40] && board[24] == board[48] && board[24]!=0) return true
    if (board[32] == board[40] && board[32] == board[48] && board[32] == board[56] && board[32]!=0) return true

    // vertical column2
    if ( board[1] == board[9]  &&  board[1] == board[17] &&  board[1] == board[25] &&  board[1]!=0) return true
    if ( board[9] == board[17] &&  board[9] == board[25] &&  board[9] == board[33] &&  board[9]!=0) return true
    if (board[17] == board[25] && board[17] == board[33] && board[17] == board[41] && board[17]!=0) return true
    if (board[25] == board[33] && board[25] == board[41] && board[25] == board[49] && board[25]!=0) return true
    if (board[33] == board[41] && board[33] == board[49] && board[33] == board[57] && board[33]!=0) return true

    // vertical column3
    if ( board[2] == board[10] &&  board[2] == board[18] &&  board[2] == board[26] &&  board[2]!=0) return true
    if (board[10] == board[18] && board[10] == board[26] && board[10] == board[34] && board[10]!=0) return true
    if (board[18] == board[26] && board[18] == board[34] && board[18] == board[42] && board[18]!=0) return true
    if (board[26] == board[34] && board[26] == board[42] && board[26] == board[50] && board[26]!=0) return true
    if (board[34] == board[42] && board[34] == board[50] && board[34] == board[58] && board[34]!=0) return true

    // vertical column4
    if ( board[3] == board[11] &&  board[3] == board[19] &&  board[3] == board[27] &&  board[3]!=0) return true
    if (board[11] == board[19] && board[11] == board[27] && board[11] == board[35] && board[11]!=0) return true
    if (board[19] == board[27] && board[19] == board[35] && board[19] == board[43] && board[19]!=0) return true
    if (board[27] == board[35] && board[27] == board[43] && board[27] == board[51] && board[27]!=0) return true
    if (board[35] == board[43] && board[35] == board[51] && board[35] == board[59] && board[35]!=0) return true

    // vertical column5
    if ( board[4] == board[12] &&  board[4] == board[20] &&  board[4] == board[28] &&  board[4]!=0) return true
    if (board[12] == board[20] && board[12] == board[28] && board[12] == board[36] && board[12]!=0) return true
    if (board[20] == board[28] && board[20] == board[36] && board[20] == board[44] && board[20]!=0) return true
    if (board[28] == board[36] && board[28] == board[44] && board[28] == board[52] && board[28]!=0) return true
    if (board[36] == board[44] && board[36] == board[52] && board[36] == board[60] && board[36]!=0) return true

    // vertical column6
    if ( board[5] == board[13] &&  board[5] == board[21] &&  board[5] == board[29] &&  board[5]!=0) return true
    if (board[13] == board[21] && board[13] == board[29] && board[13] == board[37] && board[13]!=0) return true
    if (board[21] == board[29] && board[21] == board[37] && board[21] == board[45] && board[21]!=0) return true
    if (board[29] == board[37] && board[29] == board[45] && board[29] == board[53] && board[29]!=0) return true
    if (board[37] == board[45] && board[37] == board[53] && board[37] == board[61] && board[37]!=0) return true

    // vertical column7
    if ( board[6] == board[14] &&  board[6] == board[22] &&  board[6] == board[30] &&  board[6]!=0) return true
    if (board[14] == board[22] && board[14] == board[30] && board[14] == board[38] && board[14]!=0) return true
    if (board[22] == board[30] && board[22] == board[38] && board[22] == board[46] && board[22]!=0) return true
    if (board[30] == board[38] && board[30] == board[46] && board[30] == board[54] && board[30]!=0) return true
    if (board[38] == board[46] && board[38] == board[54] && board[38] == board[62] && board[38]!=0) return true

    // vertical column8
    if ( board[7] == board[15] &&  board[7] == board[23] &&  board[7] == board[31] &&  board[7]!=0) return true
    if (board[15] == board[23] && board[15] == board[31] && board[15] == board[39] && board[15]!=0) return true
    if (board[23] == board[31] && board[23] == board[39] && board[23] == board[47] && board[23]!=0) return true
    if (board[31] == board[39] && board[31] == board[47] && board[31] == board[55] && board[31]!=0) return true
    if (board[39] == board[47] && board[39] == board[55] && board[39] == board[63] && board[39]!=0) return true

    // diagonal
    if ( board[32] == board[41] &&  board[32] == board[50] &&  board[32] == board[59] &&  board[32]!=0) return true
    if ( board[24] == board[33] &&  board[24] == board[42] &&  board[24] == board[51] &&  board[24]!=0) return true
    if ( board[33] == board[42] &&  board[33] == board[51] &&  board[33] == board[60] &&  board[33]!=0) return true
    if ( board[16] == board[25] &&  board[16] == board[34] &&  board[16] == board[43] &&  board[16]!=0) return true
    if ( board[25] == board[34] &&  board[25] == board[43] &&  board[25] == board[52] &&  board[25]!=0) return true
    if ( board[34] == board[43] &&  board[34] == board[52] &&  board[34] == board[61] &&  board[34]!=0) return true
    if ( board[8] == board[17] &&  board[8] == board[26] &&  board[8] == board[35] &&  board[8]!=0) return true
    if ( board[17] == board[26] &&  board[17] == board[35] &&  board[17] == board[44] &&  board[17]!=0) return true
    if ( board[26] == board[35] &&  board[26] == board[44] &&  board[26] == board[53] &&  board[26]!=0) return true
    if ( board[35] == board[44] &&  board[35] == board[53] &&  board[35] == board[62] &&  board[35]!=0) return true
    if ( board[0] == board[9] &&  board[0] == board[18] &&  board[0] == board[27] &&  board[0]!=0) return true
    if ( board[9] == board[18] &&  board[9] == board[27] &&  board[9] == board[36] &&  board[9]!=0) return true
    if ( board[18] == board[27] &&  board[18] == board[36] &&  board[18] == board[45] &&  board[18]!=0) return true
    if ( board[27] == board[36] &&  board[27] == board[45] &&  board[27] == board[54] &&  board[27]!=0) return true
    if ( board[36] == board[45] &&  board[36] == board[54] &&  board[36] == board[63] &&  board[36]!=0) return true
    if ( board[1] == board[10] &&  board[1] == board[19] &&  board[1] == board[28] &&  board[1]!=0) return true
    if ( board[10] == board[19] &&  board[10] == board[28] &&  board[10] == board[37] &&  board[10]!=0) return true
    if ( board[19] == board[28] &&  board[19] == board[37] &&  board[19] == board[46] &&  board[19]!=0) return true
    if ( board[28] == board[37] &&  board[28] == board[46] &&  board[28] == board[55] &&  board[28]!=0) return true
    if ( board[2] == board[11] &&  board[2] == board[20] &&  board[2] == board[29] &&  board[2]!=0) return true
    if ( board[11] == board[20] &&  board[11] == board[29] &&  board[11] == board[38] &&  board[11]!=0) return true
    if ( board[20] == board[29] &&  board[20] == board[38] &&  board[20] == board[47] &&  board[20]!=0) return true
    if ( board[3] == board[12] &&  board[3] == board[21] &&  board[3] == board[30] && board[3]!=0) return true
    if ( board[12] == board[21] &&  board[12] == board[30] &&  board[12] == board[39] &&  board[12]!=0) return true
    if ( board[4] == board[13] &&  board[4] == board[22] &&  board[4] == board[31] && board[4]!=0) return true

    return false
}

fun finishSeen(id: String, player: Int) {

    if (player == 1) {
        val resultData = FirebaseFirestore.getInstance()
            .collection("Games")
            .document(id)
            .update("finishSeen1", true)
    }

    if (player == 2) {
        val resultData = FirebaseFirestore.getInstance()
            .collection("Games")
            .document(id)
            .update("finishSeen2", true)
    }

}
