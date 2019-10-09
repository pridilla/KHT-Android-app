package com.example.khtappka

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

// https://github.com/jkcclemens/khttp/blob/master/LICENSE

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n", "ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val userLocalStore = UserLocalStore(this)
        if(userLocalStore.getUserLoggedIn()){
            startActivity(Intent(this, DashboardActivity::class.java))
        }
        else startActivity(Intent(this, LoginActivity::class.java))

        button2.setOnClickListener {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)
            }

            /*
            val st = khttp.get("http://192.168.100.85:1337")
            textView.text = st.jsonObject.get("msg").toString()
            println(st.jsonObject.get("msg"))
             */
        }
    }
}

/*
/test = GET - message - succes/unsucces
/login = POST - username: ... - password: ... (odpoved message - Usernamenotfound, Passwordiswrong, success, username, id, flag)
/register = POST - username: ... - password: ... - flag: ... - email: ...
/user = POST id: ... (message - success, IDnotfound, id, username, flag, email)
 */
