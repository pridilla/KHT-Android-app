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

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n", "ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val userLocalStore = UserLocalStore(this)
        if(userLocalStore.getUserLoggedIn()){
            startActivity(Intent(this, DashboardActivity::class.java))
        }
        else startActivity(Intent(this, LoginActivity::class.java))
    }
}