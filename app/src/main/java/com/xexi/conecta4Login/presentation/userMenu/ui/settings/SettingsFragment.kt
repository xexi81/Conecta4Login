package com.xexi.conecta4Login.presentation.userMenu.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.xexi.conecta4Login.R
import com.xexi.conecta4Login.base.Resource
import com.xexi.conecta4Login.data.userMenu.RepoImplFirestoreUsers
import com.xexi.conecta4Login.databinding.FragmentSettingsBinding
import com.xexi.conecta4Login.domain.userMenu.FirestoreUsersImpl


class SettingsFragment : Fragment() {

    private val settingsViewModel by lazy { ViewModelProvider(this, SettingsViewModelFactory(FirestoreUsersImpl(RepoImplFirestoreUsers()))).get(SettingsViewModel::class.java) }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        val mTxtUser = root.findViewById<TextView>(R.id.txtUser)
        val mSwNotification = root.findViewById<Switch>(R.id.swNotifications)
        val mBtnSaveSettings = root.findViewById<Button>(R.id.btnSaveSettings)

        settingsViewModel.firestoreUser.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    if (it.data.username.isNullOrEmpty()) {
                        mTxtUser.hint = it.data.email
                    } else mTxtUser.setText(it.data.username)

                    mSwNotification.isChecked = it.data.notifications
                }

                is Resource.Failure -> {
                    Snackbar.make(mBtnSaveSettings, "No found user", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show()
                }
            }
        })


        mSwNotification.setOnClickListener {
            mSwNotification.isChecked = !mSwNotification.isChecked
        }


        mBtnSaveSettings.setOnClickListener {
            settingsViewModel.updateFirestoreUser(mTxtUser.text.toString(), mSwNotification.isChecked).observe(viewLifecycleOwner, Observer {
                if (it is Resource.Success) {
                    requireActivity().finish()
                }

                if (it is Resource.Failure) {
                    Snackbar.make(mBtnSaveSettings, "Something was wrong in update options", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show()
                }
            })
        }

        return root
    }
}
