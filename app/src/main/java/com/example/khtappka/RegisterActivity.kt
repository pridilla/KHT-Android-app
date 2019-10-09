package com.example.khtappka

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_register)

        this.activity_register_button.setOnClickListener {
            val etUsername = this.activity_register_user_view.text.toString()
            val etPassword = this.activity_register_pass1_view.text.toString()
            val etPassControl = this.activity_register_pass2_view.text.toString()
            val etEmail = this.activity_register_email_view.text.toString()
            val registeredUser = User(etUsername, etPassword)
        }

    }
}