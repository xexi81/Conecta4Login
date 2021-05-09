package com.xexi.conecta4Login.presentation.userMenu.ui.home

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Color.parseColor
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.xexi.conecta4Login.R
import com.xexi.conecta4Login.data.games.Games
import com.xexi.conecta4Login.data.login.getUserUid
import com.xexi.conecta4Login.databinding.ItemGamesBinding
import com.xexi.conecta4Login.presentation.game.GameClass


class GameAdapter(
    private val gamesList: List<Games>,
    private val context: Context,
    private val activity: Activity,
    private val fragment: Fragment
) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {


    private val onClickListener: View.OnClickListener = View.OnClickListener { view ->
        val gameClass = GameClass(context, fragment)
        gameClass.showDialog(view.tag as String)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_games, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(gamesList[position], context, activity, fragment)

        // guardamos el objeto en la propiedad tag, para utilizarlo en el onClick
        with(holder.itemView) {
            tag = gamesList[position].id
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount(): Int {
        return gamesList.size
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemGamesBinding.bind(view)

        fun render(games: Games, context: Context, activity: Activity, fragment: Fragment) {
            with(binding) {
                val uidPlayer = getUserUid()

                if (uidPlayer == games.uid1) {
                    // Photo
                    if (games.photo2.isNullOrBlank()) {
                        ivUserPhoto.setImageResource(R.drawable.ic_baseline_help_24)
                    } else {
                        Picasso.get().load(games.photo2).into(ivUserPhoto)
                    }

                    // Name
                    if (games.name2.isEmpty() || games.name2.isBlank()) {
                        txtUsername.text = context.getString(R.string.looking)
                    } else txtUsername.text = games.name2

                    ivTurnBackgroud.setImageResource(R.drawable.ficha_roja)

                } else {
                    // Photo
                    if (games.photo1.isNullOrBlank()) {
                        ivUserPhoto.setImageResource(R.drawable.ic_baseline_help_24)
                    } else {
                        Picasso.get().load(games.photo1).into(ivUserPhoto)
                    }

                    // Name
                    if (games.name1.isEmpty()) {
                        txtUsername.text = R.string.looking.toString()
                    } else txtUsername.text = games.name1

                    ivTurnBackgroud.setImageResource(R.drawable.ficha_amarilla)
                }


                if ((uidPlayer == games.uid1 && games.turnPlayer == 1) || (uidPlayer == games.uid2 && games.turnPlayer == 2)) {
                    ivTurn.visibility = View.VISIBLE
                } else {
                    ivTurn.visibility = View.GONE
                }

            }
        }


    }
}

