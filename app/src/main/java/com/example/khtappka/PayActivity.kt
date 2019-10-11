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
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_pay.*


class PayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_pay)

        val userLocalStore = UserLocalStore(this)
        val user = userLocalStore.getLoggedInUser()
        this.dbalance.text = user.getBalance()

        this.ppay.setOnClickListener {
            val etAmount = this.pamount.toString()
            val etTarget = this.precipient.toString()
            val res = user.transfer(etAmount, etTarget)
            if (res){
                val intent = Intent(this, ConfPayActivity::class.java)
                intent.putExtra("target", etTarget)
                intent.putExtra("amount", etAmount)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, user.message, Toast.LENGTH_LONG).show()
            }
        }

        this.drefresh.setOnClickListener {
            this.dbalance.text = user.getBalance()
        }
    }
}