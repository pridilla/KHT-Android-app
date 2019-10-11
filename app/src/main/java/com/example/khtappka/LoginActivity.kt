package com.example.khtappka


import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()
        val userLocalStore = UserLocalStore(this)
        LmesText.text = ""

        this.Lreg.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        this.Llog.setOnClickListener {
            val etUsername = this.LuserText.text.toString()
            val etPassword = this.LpassText.text.toString()

            val loginUser = User(etUsername,etPassword)

            if (loginUser.login()){
                userLocalStore.storeUserData(loginUser)
                userLocalStore.setUserLoggedIn(true)

                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, loginUser.message, Toast.LENGTH_LONG).show()
                //LmesText.text = loginUser.message
            }
        }
    }
}
