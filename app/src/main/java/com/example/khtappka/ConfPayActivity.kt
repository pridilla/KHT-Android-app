package com.example.khtappka

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_confpay.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.text.Editable
import android.app.Activity




class ConfPayActivity : AppCompatActivity() {
    var st = true
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.d("KHT", "conf")
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_confpay)

        var bundle :Bundle ?=intent.extras

        if(bundle != null){
            this.camount.text = bundle.getString("amount") as String + " kht"
            this.cmes.text = "to " + bundle.getString("target") as String + "\nhave been"
        }

        this.ctime.text = dateToTime()

        fun updateEd(s: String) {
            this.ctime.text = s
        }

        val thread = object : Thread() {

            override fun run() {
                try {
                    while (st) {
                        Thread.sleep(1000)
                        runOnUiThread {
                            updateEd(dateToTime())
                        }
                    }
                } catch (e: InterruptedException) {
                }
            }
        }

        thread.start()
        this.cok.setOnClickListener {
            st = false
            thread.join()
            startActivity(Intent(this, DashboardActivity::class.java))
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    fun dateToTime(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString()
    }

}