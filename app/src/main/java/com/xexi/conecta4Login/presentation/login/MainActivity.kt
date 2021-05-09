package com.xexi.conecta4Login.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.xexi.conecta4Login.R
import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.base.checkEmpty
import com.xexi.conecta4Login.base.startActivity
import com.xexi.conecta4Login.base.toast
import com.xexi.conecta4Login.data.login.RepoImplGetUserLogged
import com.xexi.conecta4Login.databinding.ActivityMainBinding
import com.xexi.conecta4Login.domain.login.GetUserLoginImpl
import com.xexi.conecta4Login.presentation.login.viewModel.MainViewModel
import com.xexi.conecta4Login.presentation.login.viewModel.MainViewModelFactory
import com.xexi.conecta4Login.presentation.userMenu.UserMenuActivity

class MainActivity : AppCompatActivity() {
    private val mainViewModel by lazy { ViewModelProvider(this, MainViewModelFactory(GetUserLoginImpl(RepoImplGetUserLogged()))).get(MainViewModel::class.java) }
    private val GOOGLE_SIGN_IN = 1

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

        // Send password by email
        binding.txtRememberPassword.setOnClickListener {
            rememberPassword(binding)
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
        mainViewModel.user.observe(this, {
            if (it.email.isNotEmpty()) {
                startUserMenu()
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
            if (it is Resource.Success && it.data) {
                this.toast("User registered successfully")
            } else {
                if (it is Resource.Success && !it.data) {
                    this.toast("Register user -- Something went wrong!")
                }
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
            if (it is Resource.Success && it.data) {
                startUserMenu()
            } else {
                if (it is Resource.Success && !it.data) {
                    this.toast("Login user -- Something went wrong!")
                }
            }
        })
    }


    private fun rememberPassword(binding: ActivityMainBinding) {
        if (binding.txtUsername.checkEmpty()) {
            this.toast("Email is empty")
        }

        mainViewModel.rememberUser(binding.txtUsername.text.toString()).observe(this, {
            if (it is Resource.Success && it.data) {
                this.toast("Email sent")
            } else {
                if (it is Resource.Success && !it.data) {
                    this.toast("Reset user -- Something went wrong!")
                }
            }
        })
    }


    private fun loginGoogleUser(binding: ActivityMainBinding) {
        val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        val googleClient = GoogleSignIn.getClient(this, googleConf)
        ActivityCompat.startActivityForResult(this, googleClient.signInIntent, GOOGLE_SIGN_IN, null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == GOOGLE_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result!!.isSuccess) {
                val account = result.signInAccount
                mainViewModel.loginWithGoogle(account!!).observe(this, {
                    if (it is Resource.Success && it.data) {
                        startUserMenu()
                    }
                })
            } else {
                this.toast("Google login -- Something went wrong!")
            }
        }
    }

    private fun startUserMenu() {
        this.startActivity(UserMenuActivity::class.java)
    }

}


