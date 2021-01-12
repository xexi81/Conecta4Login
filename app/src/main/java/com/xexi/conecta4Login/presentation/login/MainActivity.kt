package com.xexi.conecta4Login.presentation.login

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xexi.conecta4Login.R
import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.login.RepoImplGetUserLogged
import com.xexi.conecta4Login.domain.login.GetUserLoginImpl
import com.xexi.conecta4Login.presentation.login.viewModel.MainViewModel
import com.xexi.conecta4Login.presentation.login.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    // declaramos la clase del viewModel como lateinit porque no la vamos a inicializar todavía
    private val mainViewModel by lazy {ViewModelProvider(this, MainViewModelFactory(GetUserLoginImpl(RepoImplGetUserLogged()))).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeData()
    }



    private fun observeData() {

        mainViewModel.fetchVersionCode.observe(this, Observer {

            when(it) {
                is Resource.Loading -> {
                    Log.d("Sergio", "Estamos cargando")
                }
                is Resource.Success -> {
                    val user = it.data
                    if (user.email == "") {
                        Log.d("Sergio", "el user es null")
                    } else {
                        Log.d("Sergio", user.email)
                        Log.d("Sergio", user.name)
                        Log.d("Sergio", user.uid)
                    }
                }
                is Resource.Failure -> {
                    Log.w("Sergio", it.exception.message.toString())
                }
            }
        })

    }
}


