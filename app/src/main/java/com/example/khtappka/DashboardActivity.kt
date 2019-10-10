package com.example.khtappka

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dashboard.*
import okhttp3.WebSocket
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val userLocalStore = UserLocalStore(this)

        this.textView3.text = userLocalStore.getLoggedInUser().password
        this.textView2.text = userLocalStore.getLoggedInUser().username

        val socket = WebSocket("api.kosicehacktoken.sk")

        this.activity_logout_button.setOnClickListener {

            userLocalStore.clearUserData()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}