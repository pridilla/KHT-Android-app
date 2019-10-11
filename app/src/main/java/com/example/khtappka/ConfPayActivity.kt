package com.example.khtappka

import android.annotation.SuppressLint
import android.annotation.TargetApi
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
import android.os.Build
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_confpay.*
import kotlinx.android.synthetic.main.activity_pay.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class ConfPayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_confpay)
    }

    @TargetApi(Build.VERSION_CODES.O)
    fun dateToTime(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString()
    }

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        this.camount.text = intent.getStringExtra("amount") + " kht"
        this.cmes.text = "to " + intent.getStringExtra("target") + " have been"
        this.ctime.text = dateToTime()
        this.cok.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
        while (true){
            this.ctime.text = dateToTime()
            Thread.sleep(1000)
        }
    }
}