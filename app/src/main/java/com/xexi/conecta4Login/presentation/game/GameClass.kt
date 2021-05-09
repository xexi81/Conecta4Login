package com.xexi.conecta4Login.presentation.game

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.xexi.conecta4Login.R
import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.base.drawSquare
import com.xexi.conecta4Login.base.snackBar
import com.xexi.conecta4Login.data.games.Games
import com.xexi.conecta4Login.data.games.RepoGetGamesUserImpl
import com.xexi.conecta4Login.data.games.finishSeen
import com.xexi.conecta4Login.data.games.validateVictory
import com.xexi.conecta4Login.data.login.getUserUid
import com.xexi.conecta4Login.domain.games.GetGamesUserImpl
import com.xexi.conecta4Login.presentation.userMenu.ui.home.HomeViewModel
import com.xexi.conecta4Login.presentation.userMenu.ui.home.HomeViewModelFactory




class GameClass(val context: Context, private val fragment: Fragment) {

    private val dialog = Dialog(context)
    private val homeViewModel by lazy {
        ViewModelProvider(
            fragment, HomeViewModelFactory(
                GetGamesUserImpl(
                    RepoGetGamesUserImpl()
                )
            )
        ).get(HomeViewModel::class.java)
    }

    lateinit var mIvPlayer1: ImageView
    lateinit var mIvPlayer2: ImageView
    lateinit var ivTurn: ImageView
    lateinit var mll11: LinearLayout
    lateinit var mll12: LinearLayout
    lateinit var mll13: LinearLayout
    lateinit var mll14: LinearLayout
    lateinit var mll15: LinearLayout
    lateinit var mll16: LinearLayout
    lateinit var mll17: LinearLayout
    lateinit var mll18: LinearLayout
    lateinit var mll21: LinearLayout
    lateinit var mll22: LinearLayout
    lateinit var mll23: LinearLayout
    lateinit var mll24: LinearLayout
    lateinit var mll25: LinearLayout
    lateinit var mll26: LinearLayout
    lateinit var mll27: LinearLayout
    lateinit var mll28: LinearLayout
    lateinit var mll31: LinearLayout
    lateinit var mll32: LinearLayout
    lateinit var mll33: LinearLayout
    lateinit var mll34: LinearLayout
    lateinit var mll35: LinearLayout
    lateinit var mll36: LinearLayout
    lateinit var mll37: LinearLayout
    lateinit var mll38: LinearLayout
    lateinit var mll41: LinearLayout
    lateinit var mll42: LinearLayout
    lateinit var mll43: LinearLayout
    lateinit var mll44: LinearLayout
    lateinit var mll45: LinearLayout
    lateinit var mll46: LinearLayout
    lateinit var mll47: LinearLayout
    lateinit var mll48: LinearLayout
    lateinit var mll51: LinearLayout
    lateinit var mll52: LinearLayout
    lateinit var mll53: LinearLayout
    lateinit var mll54: LinearLayout
    lateinit var mll55: LinearLayout
    lateinit var mll56: LinearLayout
    lateinit var mll57: LinearLayout
    lateinit var mll58: LinearLayout
    lateinit var mll61: LinearLayout
    lateinit var mll62: LinearLayout
    lateinit var mll63: LinearLayout
    lateinit var mll64: LinearLayout
    lateinit var mll65: LinearLayout
    lateinit var mll66: LinearLayout
    lateinit var mll67: LinearLayout
    lateinit var mll68: LinearLayout
    lateinit var mll71: LinearLayout
    lateinit var mll72: LinearLayout
    lateinit var mll73: LinearLayout
    lateinit var mll74: LinearLayout
    lateinit var mll75: LinearLayout
    lateinit var mll76: LinearLayout
    lateinit var mll77: LinearLayout
    lateinit var mll78: LinearLayout
    lateinit var mll81: LinearLayout
    lateinit var mll82: LinearLayout
    lateinit var mll83: LinearLayout
    lateinit var mll84: LinearLayout
    lateinit var mll85: LinearLayout
    lateinit var mll86: LinearLayout
    lateinit var mll87: LinearLayout
    lateinit var mll88: LinearLayout
    lateinit var mficha0101: ImageView
    lateinit var mficha0102: ImageView
    lateinit var mficha0103: ImageView
    lateinit var mficha0104: ImageView
    lateinit var mficha0105: ImageView
    lateinit var mficha0106: ImageView
    lateinit var mficha0107: ImageView
    lateinit var mficha0108: ImageView
    lateinit var mficha0201: ImageView
    lateinit var mficha0202: ImageView
    lateinit var mficha0203: ImageView
    lateinit var mficha0204: ImageView
    lateinit var mficha0205: ImageView
    lateinit var mficha0206: ImageView
    lateinit var mficha0207: ImageView
    lateinit var mficha0208: ImageView
    lateinit var mficha0301: ImageView
    lateinit var mficha0302: ImageView
    lateinit var mficha0303: ImageView
    lateinit var mficha0304: ImageView
    lateinit var mficha0305: ImageView
    lateinit var mficha0306: ImageView
    lateinit var mficha0307: ImageView
    lateinit var mficha0308: ImageView
    lateinit var mficha0401: ImageView
    lateinit var mficha0402: ImageView
    lateinit var mficha0403: ImageView
    lateinit var mficha0404: ImageView
    lateinit var mficha0405: ImageView
    lateinit var mficha0406: ImageView
    lateinit var mficha0407: ImageView
    lateinit var mficha0408: ImageView
    lateinit var mficha0501: ImageView
    lateinit var mficha0502: ImageView
    lateinit var mficha0503: ImageView
    lateinit var mficha0504: ImageView
    lateinit var mficha0505: ImageView
    lateinit var mficha0506: ImageView
    lateinit var mficha0507: ImageView
    lateinit var mficha0508: ImageView
    lateinit var mficha0601: ImageView
    lateinit var mficha0602: ImageView
    lateinit var mficha0603: ImageView
    lateinit var mficha0604: ImageView
    lateinit var mficha0605: ImageView
    lateinit var mficha0606: ImageView
    lateinit var mficha0607: ImageView
    lateinit var mficha0608: ImageView
    lateinit var mficha0701: ImageView
    lateinit var mficha0702: ImageView
    lateinit var mficha0703: ImageView
    lateinit var mficha0704: ImageView
    lateinit var mficha0705: ImageView
    lateinit var mficha0706: ImageView
    lateinit var mficha0707: ImageView
    lateinit var mficha0708: ImageView
    lateinit var mficha0801: ImageView
    lateinit var mficha0802: ImageView
    lateinit var mficha0803: ImageView
    lateinit var mficha0804: ImageView
    lateinit var mficha0805: ImageView
    lateinit var mficha0806: ImageView
    lateinit var mficha0807: ImageView
    lateinit var mficha0808: ImageView



    fun showDialog(id: String) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.game_layout)

        homeViewModel.gameData(id).observe(fragment, Observer {
            when (it) {
                is Resource.Success -> {
                    val uidPlayer = getUserUid()

                    if (it.data.winner!=null) {
                        Log.d("Sergio", "entro por victoria")
                        val mLinearLayout3 = dialog.findViewById<LinearLayout>(R.id.linearLayout3)
                        val mTxtFinish = dialog.findViewById<TextView>(R.id.txtFinish)
                        val mIvCrown = dialog.findViewById<ImageView>(R.id.ivCrown)
                        val mIvPlayer1 = dialog.findViewById<ImageView>(R.id.ivPlayer1)
                        val mIvPlayer2 = dialog.findViewById<ImageView>(R.id.ivPlayer2)
                        val mIvPlayer3 = dialog.findViewById<ImageView>(R.id.ivPlayer3)

                        mLinearLayout3.visibility = View.GONE
                        mTxtFinish.visibility = View.VISIBLE
                        mIvCrown.visibility = View.VISIBLE
                        mIvCrown.bringToFront()

                        Log.d("Sergio", "antes del literal de victoria")

                        if ((uidPlayer == it.data.uid1 && it.data.winner == 1) ||
                            (uidPlayer == it.data.uid2 && it.data.winner == 2)) {
                            mTxtFinish.text = "YOU WIN!!"
                        } else {
                            mTxtFinish.text = "YOU LOOSE!!"
                        }

                        Log.d("Sergio", "antes de cargar foto3")
                        if (it.data.winner == 1) {
                            Log.d("Sergio", "cargamos foto del 1")
                            Picasso.get().load(it.data.photo1).into(mIvPlayer3)
                        } else {
                            Log.d("Sergio", "cargamos foto del 2")
                            Picasso.get().load(it.data.photo2).into(mIvPlayer3)
                        }

                        Log.d("Sergio", "modifico vistas de fotos para victorias")
                        mIvPlayer1.visibility = View.GONE
                        mIvPlayer2.visibility = View.GONE
                        mIvPlayer3.visibility = View.VISIBLE

                        Log.d("Sergio", "el uidPlayer es $uidPlayer")
                        Log.d("Sergio", "el dataui1 es ${it.data.uid1}")
                        Log.d("Sergio", "el dataui2 es ${it.data.uid2}")
                        Log.d("Sergio", "el finishSeen1 es ${it.data.finishSeen1}")
                        Log.d("Sergio", "el finishSeen2 es ${it.data.finishSeen2}")

                        if  (uidPlayer == it.data.uid1 && !it.data.finishSeen1) {
                            Log.d("Sergio", "entro para ponerlo como visto1")
                            finishSeen(id, 1)
                        }

                        if  (uidPlayer == it.data.uid2 && !it.data.finishSeen2) {
                            Log.d("Sergio", "entro para ponerlo como visto2")
                            finishSeen(id, 2)
                        }
                    }

                    // Instantiate views
                    initializeViews()

                    // Image of player
                    if (it.data.winner==null) {
                        if ((uidPlayer == it.data.uid1 && it.data.turnPlayer == 1) ||
                            (uidPlayer == it.data.uid2 && it.data.turnPlayer == 2)) {
                            ivTurn.visibility = View.VISIBLE
                        } else ivTurn.visibility = View.INVISIBLE

                        Picasso.get().load(it.data.photo1).into(mIvPlayer1)
                        Picasso.get().load(it.data.photo2).into(mIvPlayer2)

                        if (it.data.turnPlayer == 1) {
                            mIvPlayer2.visibility = View.VISIBLE
                            mIvPlayer1.visibility = View.INVISIBLE
                        } else {
                            mIvPlayer1.visibility = View.VISIBLE
                            mIvPlayer2.visibility = View.INVISIBLE
                        }

                        // InitializeTiles, visibility GONE in all ImageViews
                        initializeTiles()

                        // InitializeLayouts, Yellow or Red tiles
                        initializeLayouts(it.data.board)

                        // Last move animation
                        if (it.data.turnPlayer == 1) {
                            lastMoveAnimation(it.data, mIvPlayer2, mIvPlayer1)
                        } else {
                            lastMoveAnimation(it.data, mIvPlayer1, mIvPlayer2)
                        }

                        mll11.setOnClickListener { tileMove(it, 0, id)}
                        mll12.setOnClickListener { tileMove(it, 1, id)}
                        mll13.setOnClickListener { tileMove(it, 2, id)}
                        mll14.setOnClickListener { tileMove(it, 3, id)}
                        mll15.setOnClickListener { tileMove(it, 4, id)}
                        mll16.setOnClickListener { tileMove(it, 5, id)}
                        mll17.setOnClickListener { tileMove(it, 6, id)}
                        mll18.setOnClickListener { tileMove(it, 7, id)}
                        mll21.setOnClickListener { tileMove(it, 8, id)}
                        mll22.setOnClickListener { tileMove(it, 9, id)}
                        mll23.setOnClickListener { tileMove(it, 10, id)}
                        mll24.setOnClickListener { tileMove(it, 11, id)}
                        mll25.setOnClickListener { tileMove(it, 12, id)}
                        mll26.setOnClickListener { tileMove(it, 13, id)}
                        mll27.setOnClickListener { tileMove(it, 14, id)}
                        mll28.setOnClickListener { tileMove(it, 15, id)}
                        mll31.setOnClickListener { tileMove(it, 16, id)}
                        mll32.setOnClickListener { tileMove(it, 17, id)}
                        mll33.setOnClickListener { tileMove(it, 18, id)}
                        mll34.setOnClickListener { tileMove(it, 19, id)}
                        mll35.setOnClickListener { tileMove(it, 20, id)}
                        mll36.setOnClickListener { tileMove(it, 21, id)}
                        mll37.setOnClickListener { tileMove(it, 22, id)}
                        mll38.setOnClickListener { tileMove(it, 23, id)}
                        mll41.setOnClickListener { tileMove(it, 24, id)}
                        mll42.setOnClickListener { tileMove(it, 25, id)}
                        mll43.setOnClickListener { tileMove(it, 26, id)}
                        mll44.setOnClickListener { tileMove(it, 27, id)}
                        mll45.setOnClickListener { tileMove(it, 28, id)}
                        mll46.setOnClickListener { tileMove(it, 29, id)}
                        mll47.setOnClickListener { tileMove(it, 30, id)}
                        mll48.setOnClickListener { tileMove(it, 31, id)}
                        mll51.setOnClickListener { tileMove(it, 32, id)}
                        mll52.setOnClickListener { tileMove(it, 33, id)}
                        mll53.setOnClickListener { tileMove(it, 34, id)}
                        mll54.setOnClickListener { tileMove(it, 35, id)}
                        mll55.setOnClickListener { tileMove(it, 36, id)}
                        mll56.setOnClickListener { tileMove(it, 37, id)}
                        mll57.setOnClickListener { tileMove(it, 38, id)}
                        mll58.setOnClickListener { tileMove(it, 39, id)}
                        mll61.setOnClickListener { tileMove(it, 40, id)}
                        mll62.setOnClickListener { tileMove(it, 41, id)}
                        mll63.setOnClickListener { tileMove(it, 42, id)}
                        mll64.setOnClickListener { tileMove(it, 43, id)}
                        mll65.setOnClickListener { tileMove(it, 44, id)}
                        mll66.setOnClickListener { tileMove(it, 45, id)}
                        mll67.setOnClickListener { tileMove(it, 46, id)}
                        mll68.setOnClickListener { tileMove(it, 47, id)}
                        mll71.setOnClickListener { tileMove(it, 48, id)}
                        mll72.setOnClickListener { tileMove(it, 49, id)}
                        mll73.setOnClickListener { tileMove(it, 50, id)}
                        mll74.setOnClickListener { tileMove(it, 51, id)}
                        mll75.setOnClickListener { tileMove(it, 52, id)}
                        mll76.setOnClickListener { tileMove(it, 53, id)}
                        mll77.setOnClickListener { tileMove(it, 54, id)}
                        mll78.setOnClickListener { tileMove(it, 55, id)}
                        mll81.setOnClickListener { tileMove(it, 56, id)}
                        mll82.setOnClickListener { tileMove(it, 57, id)}
                        mll83.setOnClickListener { tileMove(it, 58, id)}
                        mll84.setOnClickListener { tileMove(it, 59, id)}
                        mll85.setOnClickListener { tileMove(it, 60, id)}
                        mll86.setOnClickListener { tileMove(it, 61, id)}
                        mll87.setOnClickListener { tileMove(it, 62, id)}
                        mll88.setOnClickListener { tileMove(it, 63, id)}
                    }
                }

                is Resource.Failure -> Log.d("Sergio", "Failure recogiendo EL JUEGO")
                is Resource.Loading -> Log.d("Sergio", "Loading recogiendo EL JUEGO")
                else -> Log.d("Sergio", "Otra cosa está pasando Games")
            }
        })

        dialog.show()
    }

    private fun tileMove(view: View, position: Int, id: String) {
        homeViewModel.moveTile(id, position).observe(fragment, Observer {

            when (it) {
                is Resource.Success -> {
                    Log.d("Sergio", "el juego ha ido bien")
                }
                is Resource.Failure -> {
                    view.snackBar(it.exception.message.toString())
                }
                else -> { }
            }

        })
    }

    private fun animationPhoto(player1: ImageView, player2: ImageView) {
        val animatorSet = AnimatorSet()
        val translate = ObjectAnimator.ofFloat(player1, "translationX", 0f, -800f)
        translate.duration = 2000
        //2nd animation: rotate 360º
        val rotate = ObjectAnimator.ofFloat(player1, "rotationX", 0f, 360f)
        rotate.duration = 2000

        animatorSet.play(translate).with(rotate)
        animatorSet.start()

        animatorSet.doOnEnd {
            player2.visibility = View.VISIBLE
            val animatorSet2 = AnimatorSet()
            val translate2 = ObjectAnimator.ofFloat(player2, "translationX", 800f, 0f)
            translate2.duration = 2000
            val rotate2 = ObjectAnimator.ofFloat(player2, "rotationX", 0f, 360f)
            rotate2.duration = 2000

            animatorSet2.play(translate2).with(rotate2)
            animatorSet2.start()
        }
    }


    private fun lastMoveAnimation(game: Games, player1: ImageView, player2: ImageView) {
        if (game.lastPlayed==null) {
            Log.d("Sergio", "finishAnimation a true")
            animationPhoto(player1, player2)
        } else {
            prepareMoveAnimation(game.lastPlayed, game.turnPlayer, player1, player2)
        }
    }



    private fun prepareMoveAnimation(
        lastPlayed: Int,
        turnPlayer: Int,
        player1: ImageView,
        player2: ImageView
    ) {
        Log.d("Sergio", "lastPlayed $lastPlayed, turnPlayer $turnPlayer")
        when(lastPlayed) {
            0 -> playerAnimation(mll11, mficha0101, turnPlayer, -900f, 3000, player1, player2)
            1 -> playerAnimation(mll12, mficha0102, turnPlayer, -900f, 3000, player1, player2)
            2 -> playerAnimation(mll13, mficha0103, turnPlayer, -900f, 3000, player1, player2)
            3 -> playerAnimation(mll14, mficha0104, turnPlayer, -900f, 3000, player1, player2)
            4 -> playerAnimation(mll15, mficha0105, turnPlayer, -900f, 3000, player1, player2)
            5 -> playerAnimation(mll16, mficha0106, turnPlayer, -900f, 3000, player1, player2)
            6 -> playerAnimation(mll17, mficha0107, turnPlayer, -900f, 3000, player1, player2)
            7 -> playerAnimation(mll18, mficha0108, turnPlayer, -900f, 3000, player1, player2)
            8 -> playerAnimation(mll21, mficha0201, turnPlayer, -780f, 2600, player1, player2)
            9 -> playerAnimation(mll22, mficha0202, turnPlayer, -780f, 2600, player1, player2)
            10 -> playerAnimation(mll23, mficha0203, turnPlayer, -780f, 2600, player1, player2)
            11 -> playerAnimation(mll24, mficha0204, turnPlayer, -780f, 2600, player1, player2)
            12 -> playerAnimation(mll25, mficha0205, turnPlayer, -780f, 2600, player1, player2)
            13 -> playerAnimation(mll26, mficha0206, turnPlayer, -780f, 2600, player1, player2)
            14 -> playerAnimation(mll27, mficha0207, turnPlayer, -780f, 2600, player1, player2)
            15 -> playerAnimation(mll28, mficha0208, turnPlayer, -780f, 2600, player1, player2)
            16 -> playerAnimation(mll31, mficha0301, turnPlayer, -640f, 2200, player1, player2)
            17 -> playerAnimation(mll32, mficha0302, turnPlayer, -640f, 2200, player1, player2)
            18 -> playerAnimation(mll33, mficha0303, turnPlayer, -640f, 2200, player1, player2)
            19 -> playerAnimation(mll34, mficha0304, turnPlayer, -640f, 2200, player1, player2)
            20 -> playerAnimation(mll35, mficha0305, turnPlayer, -640f, 2200, player1, player2)
            21 -> playerAnimation(mll36, mficha0306, turnPlayer, -640f, 2200, player1, player2)
            22 -> playerAnimation(mll37, mficha0307, turnPlayer, -640f, 2200, player1, player2)
            23 -> playerAnimation(mll38, mficha0308, turnPlayer, -640f, 2200, player1, player2)
            24 -> playerAnimation(mll41, mficha0401, turnPlayer, -520f, 1800, player1, player2)
            25 -> playerAnimation(mll42, mficha0402, turnPlayer, -520f, 1800, player1, player2)
            26 -> playerAnimation(mll43, mficha0403, turnPlayer, -520f, 1800, player1, player2)
            27 -> playerAnimation(mll44, mficha0404, turnPlayer, -520f, 1800, player1, player2)
            28 -> playerAnimation(mll45, mficha0405, turnPlayer, -520f, 1800, player1, player2)
            29 -> playerAnimation(mll46, mficha0406, turnPlayer, -520f, 1800, player1, player2)
            30 -> playerAnimation(mll47, mficha0407, turnPlayer, -520f, 1800, player1, player2)
            31 -> playerAnimation(mll48, mficha0408, turnPlayer, -520f, 1800, player1, player2)
            32 -> playerAnimation(mll51, mficha0501, turnPlayer, -400f, 1400, player1, player2)
            33 -> playerAnimation(mll52, mficha0502, turnPlayer, -400f, 1400, player1, player2)
            34 -> playerAnimation(mll53, mficha0503, turnPlayer, -400f, 1400, player1, player2)
            35 -> playerAnimation(mll54, mficha0504, turnPlayer, -400f, 1400, player1, player2)
            36 -> playerAnimation(mll55, mficha0505, turnPlayer, -400f, 1400, player1, player2)
            37 -> playerAnimation(mll56, mficha0506, turnPlayer, -400f, 1400, player1, player2)
            38 -> playerAnimation(mll57, mficha0507, turnPlayer, -400f, 1400, player1, player2)
            39 -> playerAnimation(mll58, mficha0508, turnPlayer, -400f, 1400, player1, player2)
            40 -> playerAnimation(mll61, mficha0601, turnPlayer, -280f, 1200, player1, player2)
            41 -> playerAnimation(mll62, mficha0602, turnPlayer, -280f, 1200, player1, player2)
            42 -> playerAnimation(mll63, mficha0603, turnPlayer, -280f, 1200, player1, player2)
            43 -> playerAnimation(mll64, mficha0604, turnPlayer, -280f, 1200, player1, player2)
            44 -> playerAnimation(mll65, mficha0605, turnPlayer, -280f, 1200, player1, player2)
            45 -> playerAnimation(mll66, mficha0606, turnPlayer, -280f, 1200, player1, player2)
            46 -> playerAnimation(mll67, mficha0607, turnPlayer, -280f, 1200, player1, player2)
            47 -> playerAnimation(mll68, mficha0608, turnPlayer, -280f, 1200, player1, player2)
            48 -> playerAnimation(mll71, mficha0701, turnPlayer, -160f, 800, player1, player2)
            49 -> playerAnimation(mll72, mficha0702, turnPlayer, -160f, 800, player1, player2)
            50 -> playerAnimation(mll73, mficha0703, turnPlayer, -160f, 800, player1, player2)
            51 -> playerAnimation(mll74, mficha0704, turnPlayer, -160f, 800, player1, player2)
            52 -> playerAnimation(mll75, mficha0705, turnPlayer, -160f, 800, player1, player2)
            53 -> playerAnimation(mll76, mficha0706, turnPlayer, -160f, 800, player1, player2)
            54 -> playerAnimation(mll77, mficha0707, turnPlayer, -160f, 800, player1, player2)
            55 -> playerAnimation(mll78, mficha0708, turnPlayer, -160f, 800, player1, player2)
            56 -> playerAnimation(mll81, mficha0801, turnPlayer, -40f, 800, player1, player2)
            57 -> playerAnimation(mll82, mficha0802, turnPlayer, -40f, 800, player1, player2)
            58 -> playerAnimation(mll83, mficha0803, turnPlayer, -40f, 800, player1, player2)
            59 -> playerAnimation(mll84, mficha0804, turnPlayer, -40f, 800, player1, player2)
            60 -> playerAnimation(mll85, mficha0805, turnPlayer, -40f, 800, player1, player2)
            61 -> playerAnimation(mll86, mficha0806, turnPlayer, -40f, 800, player1, player2)
            62 -> playerAnimation(mll87, mficha0807, turnPlayer, -40f, 800, player1, player2)
            63 -> playerAnimation(mll88, mficha0808, turnPlayer, -40f, 800, player1, player2)
        }
    }


    private fun playerAnimation(
        linearLayout: LinearLayout?,
        tile: ImageView,
        turnPlayer: Int,
        valueY: Float,
        duration: Long,
        player1: ImageView,
        player2: ImageView
    ) {
        initializeViews()
        //Animator
        tile.visibility = View.VISIBLE

        if (turnPlayer == 1) {
            tile.setImageResource(R.drawable.ficha_amarilla)
        } else {
            tile.setImageResource(R.drawable.ficha_roja)
        }

        val animatorSet = AnimatorSet()
        val translate = ObjectAnimator.ofFloat(tile, "translationY", valueY, 0f)
        translate.duration = duration

        animatorSet.play(translate)
        animatorSet.start()

        animatorSet.doOnEnd {
            tile.visibility = View.GONE
            Log.d("Sergio", "finishAnimation a true")

            if (turnPlayer == 1) {
                linearLayout!!.setBackgroundResource(R.drawable.casilla_amarilla)
            } else {
                linearLayout!!.setBackgroundResource(R.drawable.casilla_roja)
            }

            animationPhoto(player1, player2)
        }
    }

    private fun initializeLayouts(board: List<Int>) {
        initializeViews()

        mll11.drawSquare(board[0])
        mll12.drawSquare(board[1])
        mll13.drawSquare(board[2])
        mll14.drawSquare(board[3])
        mll15.drawSquare(board[4])
        mll16.drawSquare(board[5])
        mll17.drawSquare(board[6])
        mll18.drawSquare(board[7])
        mll21.drawSquare(board[8])
        mll22.drawSquare(board[9])
        mll23.drawSquare(board[10])
        mll24.drawSquare(board[11])
        mll25.drawSquare(board[12])
        mll26.drawSquare(board[13])
        mll27.drawSquare(board[14])
        mll28.drawSquare(board[15])
        mll31.drawSquare(board[16])
        mll32.drawSquare(board[17])
        mll33.drawSquare(board[18])
        mll34.drawSquare(board[19])
        mll35.drawSquare(board[20])
        mll36.drawSquare(board[21])
        mll37.drawSquare(board[22])
        mll38.drawSquare(board[23])
        mll41.drawSquare(board[24])
        mll42.drawSquare(board[25])
        mll43.drawSquare(board[26])
        mll44.drawSquare(board[27])
        mll45.drawSquare(board[28])
        mll46.drawSquare(board[29])
        mll47.drawSquare(board[30])
        mll48.drawSquare(board[31])
        mll51.drawSquare(board[32])
        mll52.drawSquare(board[33])
        mll53.drawSquare(board[34])
        mll54.drawSquare(board[35])
        mll55.drawSquare(board[36])
        mll56.drawSquare(board[37])
        mll57.drawSquare(board[38])
        mll58.drawSquare(board[39])
        mll61.drawSquare(board[40])
        mll62.drawSquare(board[41])
        mll63.drawSquare(board[42])
        mll64.drawSquare(board[43])
        mll65.drawSquare(board[44])
        mll66.drawSquare(board[45])
        mll67.drawSquare(board[46])
        mll68.drawSquare(board[47])
        mll71.drawSquare(board[48])
        mll72.drawSquare(board[49])
        mll73.drawSquare(board[50])
        mll74.drawSquare(board[51])
        mll75.drawSquare(board[52])
        mll76.drawSquare(board[53])
        mll77.drawSquare(board[54])
        mll78.drawSquare(board[55])
        mll81.drawSquare(board[56])
        mll82.drawSquare(board[57])
        mll83.drawSquare(board[58])
        mll84.drawSquare(board[59])
        mll85.drawSquare(board[60])
        mll86.drawSquare(board[61])
        mll87.drawSquare(board[62])
        mll88.drawSquare(board[63])
    }

    private fun initializeTiles() {
        initializeViews()
        mficha0101.visibility = View.GONE
        mficha0102.visibility = View.GONE
        mficha0103.visibility = View.GONE
        mficha0104.visibility = View.GONE
        mficha0105.visibility = View.GONE
        mficha0106.visibility = View.GONE
        mficha0107.visibility = View.GONE
        mficha0108.visibility = View.GONE
        mficha0201.visibility = View.GONE
        mficha0202.visibility = View.GONE
        mficha0203.visibility = View.GONE
        mficha0204.visibility = View.GONE
        mficha0205.visibility = View.GONE
        mficha0206.visibility = View.GONE
        mficha0207.visibility = View.GONE
        mficha0208.visibility = View.GONE
        mficha0301.visibility = View.GONE
        mficha0302.visibility = View.GONE
        mficha0303.visibility = View.GONE
        mficha0304.visibility = View.GONE
        mficha0305.visibility = View.GONE
        mficha0306.visibility = View.GONE
        mficha0307.visibility = View.GONE
        mficha0308.visibility = View.GONE
        mficha0401.visibility = View.GONE
        mficha0402.visibility = View.GONE
        mficha0403.visibility = View.GONE
        mficha0404.visibility = View.GONE
        mficha0405.visibility = View.GONE
        mficha0406.visibility = View.GONE
        mficha0407.visibility = View.GONE
        mficha0408.visibility = View.GONE
        mficha0501.visibility = View.GONE
        mficha0502.visibility = View.GONE
        mficha0503.visibility = View.GONE
        mficha0504.visibility = View.GONE
        mficha0505.visibility = View.GONE
        mficha0506.visibility = View.GONE
        mficha0507.visibility = View.GONE
        mficha0508.visibility = View.GONE
        mficha0601.visibility = View.GONE
        mficha0602.visibility = View.GONE
        mficha0603.visibility = View.GONE
        mficha0604.visibility = View.GONE
        mficha0605.visibility = View.GONE
        mficha0606.visibility = View.GONE
        mficha0607.visibility = View.GONE
        mficha0608.visibility = View.GONE
        mficha0701.visibility = View.GONE
        mficha0702.visibility = View.GONE
        mficha0703.visibility = View.GONE
        mficha0704.visibility = View.GONE
        mficha0705.visibility = View.GONE
        mficha0706.visibility = View.GONE
        mficha0707.visibility = View.GONE
        mficha0708.visibility = View.GONE
        mficha0801.visibility = View.GONE
        mficha0802.visibility = View.GONE
        mficha0803.visibility = View.GONE
        mficha0804.visibility = View.GONE
        mficha0805.visibility = View.GONE
        mficha0806.visibility = View.GONE
        mficha0807.visibility = View.GONE
        mficha0808.visibility = View.GONE
    }

    private fun initializeViews() {
        mIvPlayer1 = dialog.findViewById(R.id.ivPlayer1)
        mIvPlayer2 = dialog.findViewById(R.id.ivPlayer2)
        ivTurn = dialog.findViewById(R.id.ivTurn)

        mll11 = dialog.findViewById(R.id.ll11)
        mll12 = dialog.findViewById(R.id.ll12)
        mll13 = dialog.findViewById(R.id.ll13)
        mll14 = dialog.findViewById(R.id.ll14)
        mll15 = dialog.findViewById(R.id.ll15)
        mll16 = dialog.findViewById(R.id.ll16)
        mll17 = dialog.findViewById(R.id.ll17)
        mll18 = dialog.findViewById(R.id.ll18)
        mll21 = dialog.findViewById(R.id.ll21)
        mll22 = dialog.findViewById(R.id.ll22)
        mll23 = dialog.findViewById(R.id.ll23)
        mll24 = dialog.findViewById(R.id.ll24)
        mll25 = dialog.findViewById(R.id.ll25)
        mll26 = dialog.findViewById(R.id.ll26)
        mll27 = dialog.findViewById(R.id.ll27)
        mll28 = dialog.findViewById(R.id.ll28)
        mll31 = dialog.findViewById(R.id.ll31)
        mll32 = dialog.findViewById(R.id.ll32)
        mll33 = dialog.findViewById(R.id.ll33)
        mll34 = dialog.findViewById(R.id.ll34)
        mll35 = dialog.findViewById(R.id.ll35)
        mll36 = dialog.findViewById(R.id.ll36)
        mll37 = dialog.findViewById(R.id.ll37)
        mll38 = dialog.findViewById(R.id.ll38)
        mll41 = dialog.findViewById(R.id.ll41)
        mll42 = dialog.findViewById(R.id.ll42)
        mll43 = dialog.findViewById(R.id.ll43)
        mll44 = dialog.findViewById(R.id.ll44)
        mll45 = dialog.findViewById(R.id.ll45)
        mll46 = dialog.findViewById(R.id.ll46)
        mll47 = dialog.findViewById(R.id.ll47)
        mll48 = dialog.findViewById(R.id.ll48)
        mll51 = dialog.findViewById(R.id.ll51)
        mll52 = dialog.findViewById(R.id.ll52)
        mll53 = dialog.findViewById(R.id.ll53)
        mll54 = dialog.findViewById(R.id.ll54)
        mll55 = dialog.findViewById(R.id.ll55)
        mll56 = dialog.findViewById(R.id.ll56)
        mll57 = dialog.findViewById(R.id.ll57)
        mll58 = dialog.findViewById(R.id.ll58)
        mll61 = dialog.findViewById(R.id.ll61)
        mll62 = dialog.findViewById(R.id.ll62)
        mll63 = dialog.findViewById(R.id.ll63)
        mll64 = dialog.findViewById(R.id.ll64)
        mll65 = dialog.findViewById(R.id.ll65)
        mll66 = dialog.findViewById(R.id.ll66)
        mll67 = dialog.findViewById(R.id.ll67)
        mll68 = dialog.findViewById(R.id.ll68)
        mll71 = dialog.findViewById(R.id.ll71)
        mll72 = dialog.findViewById(R.id.ll72)
        mll73 = dialog.findViewById(R.id.ll73)
        mll74 = dialog.findViewById(R.id.ll74)
        mll75 = dialog.findViewById(R.id.ll75)
        mll76 = dialog.findViewById(R.id.ll76)
        mll77 = dialog.findViewById(R.id.ll77)
        mll78 = dialog.findViewById(R.id.ll78)
        mll81 = dialog.findViewById(R.id.ll81)
        mll82 = dialog.findViewById(R.id.ll82)
        mll83 = dialog.findViewById(R.id.ll83)
        mll84 = dialog.findViewById(R.id.ll84)
        mll85 = dialog.findViewById(R.id.ll85)
        mll86 = dialog.findViewById(R.id.ll86)
        mll87 = dialog.findViewById(R.id.ll87)
        mll88 = dialog.findViewById(R.id.ll88)

        mficha0101 = dialog.findViewById(R.id.ficha0101)
        mficha0102 = dialog.findViewById(R.id.ficha0102)
        mficha0103 = dialog.findViewById(R.id.ficha0103)
        mficha0104 = dialog.findViewById(R.id.ficha0104)
        mficha0105 = dialog.findViewById(R.id.ficha0105)
        mficha0106 = dialog.findViewById(R.id.ficha0106)
        mficha0107 = dialog.findViewById(R.id.ficha0107)
        mficha0108 = dialog.findViewById(R.id.ficha0108)
        mficha0201 = dialog.findViewById(R.id.ficha0201)
        mficha0202 = dialog.findViewById(R.id.ficha0202)
        mficha0203 = dialog.findViewById(R.id.ficha0203)
        mficha0204 = dialog.findViewById(R.id.ficha0204)
        mficha0205 = dialog.findViewById(R.id.ficha0205)
        mficha0206 = dialog.findViewById(R.id.ficha0206)
        mficha0207 = dialog.findViewById(R.id.ficha0207)
        mficha0208 = dialog.findViewById(R.id.ficha0208)
        mficha0301 = dialog.findViewById(R.id.ficha0301)
        mficha0302 = dialog.findViewById(R.id.ficha0302)
        mficha0303 = dialog.findViewById(R.id.ficha0303)
        mficha0304 = dialog.findViewById(R.id.ficha0304)
        mficha0305 = dialog.findViewById(R.id.ficha0305)
        mficha0306 = dialog.findViewById(R.id.ficha0306)
        mficha0307 = dialog.findViewById(R.id.ficha0307)
        mficha0308 = dialog.findViewById(R.id.ficha0308)
        mficha0401 = dialog.findViewById(R.id.ficha0401)
        mficha0402 = dialog.findViewById(R.id.ficha0402)
        mficha0403 = dialog.findViewById(R.id.ficha0403)
        mficha0404 = dialog.findViewById(R.id.ficha0404)
        mficha0405 = dialog.findViewById(R.id.ficha0405)
        mficha0406 = dialog.findViewById(R.id.ficha0406)
        mficha0407 = dialog.findViewById(R.id.ficha0407)
        mficha0408 = dialog.findViewById(R.id.ficha0408)
        mficha0501 = dialog.findViewById(R.id.ficha0501)
        mficha0502 = dialog.findViewById(R.id.ficha0502)
        mficha0503 = dialog.findViewById(R.id.ficha0503)
        mficha0504 = dialog.findViewById(R.id.ficha0504)
        mficha0505 = dialog.findViewById(R.id.ficha0505)
        mficha0506 = dialog.findViewById(R.id.ficha0506)
        mficha0507 = dialog.findViewById(R.id.ficha0507)
        mficha0508 = dialog.findViewById(R.id.ficha0508)
        mficha0601 = dialog.findViewById(R.id.ficha0601)
        mficha0602 = dialog.findViewById(R.id.ficha0602)
        mficha0603 = dialog.findViewById(R.id.ficha0603)
        mficha0604 = dialog.findViewById(R.id.ficha0604)
        mficha0605 = dialog.findViewById(R.id.ficha0605)
        mficha0606 = dialog.findViewById(R.id.ficha0606)
        mficha0607 = dialog.findViewById(R.id.ficha0607)
        mficha0608 = dialog.findViewById(R.id.ficha0608)
        mficha0701 = dialog.findViewById(R.id.ficha0701)
        mficha0702 = dialog.findViewById(R.id.ficha0702)
        mficha0703 = dialog.findViewById(R.id.ficha0703)
        mficha0704 = dialog.findViewById(R.id.ficha0704)
        mficha0705 = dialog.findViewById(R.id.ficha0705)
        mficha0706 = dialog.findViewById(R.id.ficha0706)
        mficha0707 = dialog.findViewById(R.id.ficha0707)
        mficha0708 = dialog.findViewById(R.id.ficha0708)
        mficha0801 = dialog.findViewById(R.id.ficha0801)
        mficha0802 = dialog.findViewById(R.id.ficha0802)
        mficha0803 = dialog.findViewById(R.id.ficha0803)
        mficha0804 = dialog.findViewById(R.id.ficha0804)
        mficha0805 = dialog.findViewById(R.id.ficha0805)
        mficha0806 = dialog.findViewById(R.id.ficha0806)
        mficha0807 = dialog.findViewById(R.id.ficha0807)
        mficha0808 = dialog.findViewById(R.id.ficha0808)
    }

}


