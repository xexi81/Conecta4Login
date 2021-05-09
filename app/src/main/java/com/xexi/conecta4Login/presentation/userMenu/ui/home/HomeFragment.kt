package com.xexi.conecta4Login.presentation.userMenu.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.xexi.conecta4Login.R
import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.games.RepoGetGamesUserImpl
import com.xexi.conecta4Login.data.userMenu.getFirestoreUser
import com.xexi.conecta4Login.domain.games.GetGamesUserImpl

class HomeFragment : Fragment() {

    private val homeViewModel by lazy {
        ViewModelProvider(
            this, HomeViewModelFactory(
                GetGamesUserImpl(
                    RepoGetGamesUserImpl()
                )
            )
        ).get(HomeViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val mRvGames = root.findViewById<RecyclerView>(R.id.rvGames)
        val mRvGames2 = root.findViewById<RecyclerView>(R.id.rvGames2)
        val mFab = root.findViewById<FloatingActionButton>(R.id.fab)
        val mTxtWins = root.findViewById<TextView>(R.id.txtWins)
        val mTxtLooses = root.findViewById<TextView>(R.id.txtLooses)

        homeViewModel.firestoreUser.observe(requireActivity(), Observer {
            when (it) {

                is Resource.Success -> {
                    mTxtWins.text = it.data.wins.toString()
                    mTxtLooses.text = it.data.looses.toString()
                }

                is Resource.Failure -> {
                    Log.d("Sergio", "ha ido mal al recupeRAR USUARIO")
                    mTxtWins.text = 0.toString()
                    mTxtLooses.text = 0.toString()
                }

                is Resource.Loading -> {}
                else -> Log.d("Sergio", "Otra cosa está pasando al recibir firestoreUser")
            }
        })

        homeViewModel.gamesList.observe(requireActivity(), Observer {
            when (it) {

                is Resource.Success -> {
                    mRvGames.layoutManager = LinearLayoutManager(context)
                    val adapter = GameAdapter(it.data, requireContext(), requireActivity(), this)
                    mRvGames.adapter = adapter
                    adapter.notifyDataSetChanged()
                }

                is Resource.Failure -> {}
                is Resource.Loading -> {}
                else -> Log.d("Sergio", "Otra cosa está pasando Games")
            }

        })

        homeViewModel.gamesList2.observe(requireActivity(), Observer {
            when (it) {

                is Resource.Success -> {
                    mRvGames2.layoutManager = LinearLayoutManager(context)
                    val adapter = GameAdapter(it.data, requireContext(), requireActivity(), this)
                    mRvGames2.adapter = adapter
                    adapter.notifyDataSetChanged()
                }

                is Resource.Failure -> { }
                is Resource.Loading -> {}
                else -> Log.d("Sergio", "Otra cosa está pasando Games")
            }

        })


        mFab.setOnClickListener {
            homeViewModel.newGame.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is Resource.Loading -> {
                        Log.d("Sergio", "cargando para crear juego")
                    } // ProgressBar?
                    is Resource.Success -> {
                        Log.d("Sergio", "juego creado")
                    } // Fuera progress bar
                    is Resource.Failure -> {
                        Log.d("Sergio", "error al crear juego")
                    }
                    else -> {
                        Log.d("Sergio", "ha pasado otra cosa")
                    } // SnakeBar de error??
                }
            })
        }

        return root
    }


}









