package com.example.khtappka


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val userLocalStore = UserLocalStore(this)


        this.activity_login_register_button.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        this.activity_login_button.setOnClickListener {
            val etUsername = this.activity_login_user_view.text.toString()
            val etPassword = this.activity_login_password_view.text.toString()

            userLocalStore.storeUserData(User(etUsername,etPassword))
            userLocalStore.setUserLoggedIn(true)

            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
    }
}
