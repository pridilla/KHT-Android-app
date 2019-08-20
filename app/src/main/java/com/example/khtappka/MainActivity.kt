package com.example.khtappka

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

// https://github.com/jkcclemens/khttp/blob/master/LICENSE
//testkj

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n", "ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button2.setOnClickListener {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)
            }

            var testData = mapOf(
                "username" to editText.text.toString(),
                "password" to editText2.text.toString(),
                "flag" to "1"
            )

            val st = khttp.get("http://192.168.100.85:1337")
            textView.text = st.jsonObject.get("msg").toString()
            println(st.jsonObject.get("msg"))
        }
    }
}
