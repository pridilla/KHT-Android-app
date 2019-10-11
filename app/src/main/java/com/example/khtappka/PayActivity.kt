package com.example.khtappka

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_pay.*


class PayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_pay)
        pprogressBar.visibility = View.INVISIBLE



        val userLocalStore = UserLocalStore(this)
        val user = userLocalStore.getLoggedInUser()

        fun getAmount(): String{
            return this.pamount.text.toString()
        }

        fun getRecipient() : String{
            return this.precipient.text.toString()
        }

        var mes = ""
        var res = false

        val thread = object : Thread() {
            override fun run() {
                Log.d("PROGRES", "")
                val inputManager:InputMethodManager =getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)
                val ga = getAmount()
                val gp = getRecipient()
                res = user.transfer(ga,gp)
            }
        }

        this.ppay.setOnClickListener {
            thread.start()
            this.pprogressBar.visibility = View.VISIBLE
            thread.join()
            this.pprogressBar.visibility = View.INVISIBLE

            if (res){
                val e = Intent(this, ConfPayActivity::class.java)
                e.putExtra("target", getRecipient())
                e.putExtra("amount", getAmount())
                startActivity(e)
            }

            else {
                Toast.makeText(this, user.message, Toast.LENGTH_LONG).show()
            }
        }



    }
}