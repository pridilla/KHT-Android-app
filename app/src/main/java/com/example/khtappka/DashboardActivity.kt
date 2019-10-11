package com.example.khtappka

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dashboard.*
import okhttp3.WebSocket
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.Window
import android.view.WindowManager


class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_dashboard)
    }

    override fun onResume() {
        super.onResume()
        val userLocalStore = UserLocalStore(this)
        val user = userLocalStore.getLoggedInUser()
        this.dbalance.text = user.getBalance()

        this.dpay.setOnClickListener {
            startActivity(Intent(this, PayActivity::class.java))
        }

        this.dlogout.setOnClickListener {

            userLocalStore.clearUserData()
            startActivity(Intent(this, MainActivity::class.java))
        }

        this.drefresh.setOnClickListener {
            this.dbalance.text = user.getBalance()
        }
    }
}