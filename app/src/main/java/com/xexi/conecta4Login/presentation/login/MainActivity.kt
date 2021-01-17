package com.xexi.conecta4Login.presentation.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.xexi.conecta4Login.R
import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.base.checkEmpty
import com.xexi.conecta4Login.base.toast
import com.xexi.conecta4Login.data.login.RepoImplGetUserLogged
import com.xexi.conecta4Login.databinding.ActivityMainBinding
import com.xexi.conecta4Login.domain.login.GetUserLoginImpl
import com.xexi.conecta4Login.presentation.login.viewModel.MainViewModel
import com.xexi.conecta4Login.presentation.login.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private val mainViewModel by lazy { ViewModelProvider(this, MainViewModelFactory(GetUserLoginImpl(RepoImplGetUserLogged()))).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ViewBinding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Login with firebase
        binding.btnLogin.setOnClickListener {
            loginFirebaseUser(binding)
        }

        // Register with firebase
        binding.btnRegister.setOnClickListener {
            createFirebaseUser(binding)
        }

        // Login with Google
        binding.btnGoogle.setOnClickListener {
            loginGoogleUser(binding)
        }
    }

    override fun onStart() {
        super.onStart()

        getFirebaseUser()
    }


    private fun getFirebaseUser() {
        mainViewModel.getCurrentUser.observe(this, {
            if (it is Resource.Success && it.data.email.isNotEmpty()) {
                //TODO("StartActivity to another window")
            } else {
                this.toast("Wrong email or password")
            }
        })
    }


    private fun createFirebaseUser(binding: ActivityMainBinding) {
        if (binding.txtUsername.checkEmpty()) {
            this.toast("Email is empty")
        }

        if (binding.txtPassword.checkEmpty()) {
            this.toast("Password is empty")
        }

        mainViewModel.regUser(binding.txtUsername.text.toString(), binding.txtPassword.text.toString()).observe(this, {
            if (it is Resource.Success) {
                //TODO("StartActivity to another window")
            } else {
                this.toast("Something went wrong!")
            }
        })
    }


    private fun loginFirebaseUser(binding: ActivityMainBinding) {
        if (binding.txtUsername.checkEmpty()) {
            this.toast("Email is empty")
        }

        if (binding.txtPassword.checkEmpty()) {
            this.toast("Password is empty")
        }

        mainViewModel.logUser(binding.txtUsername.text.toString(), binding.txtPassword.text.toString()).observe(this, {
            if (it is Resource.Success) {
                //TODO("StartActivity to another window")
            } else {
                this.toast("Something went wrong!")
            }
        })
    }


    private fun loginGoogleUser(binding: ActivityMainBinding) {
        mainViewModel.logGoogleUser(binding.txtUsername.text.toString(), binding.txtPassword.text.toString()).observe(this, {
            when (it) {
                is Resource.Success -> {
                    Log.d("Sergio", "Success: ${it.data}")
                }
                is Resource.Failure -> {
                    Log.w("Sergio", it.exception.message.toString())
                }
            }
        })
    }

}


