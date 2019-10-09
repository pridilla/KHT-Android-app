package com.example.khtappka

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val userLocalStore = UserLocalStore(this)

        this.textView3.text = userLocalStore.getLoggedInUser().password
        this.textView2.text = userLocalStore.getLoggedInUser().username

        this.activity_logout_button.setOnClickListener {

            userLocalStore.clearUserData()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}