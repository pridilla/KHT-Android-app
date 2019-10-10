package com.example.khtappka

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.StrictMode
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

// https://github.com/jkcclemens/khttp/blob/master/LICENSE

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n", "ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)


        val userLocalStore = UserLocalStore(this)
        if(userLocalStore.getUserLoggedIn()){
            startActivity(Intent(this, DashboardActivity::class.java))
        }
        else startActivity(Intent(this, LoginActivity::class.java))
    }
}

/*
/test = GET - message - succes/unsucces
/login = POST - username: ... - password: ... (odpoved message - Usernamenotfound, Passwordiswrong, success, username, id, flag)
/register = POST - username: ... - password: ... - flag: ... - email: ...
/user = POST id: ... (message - success, IDnotfound, id, username, flag, email)
 */
