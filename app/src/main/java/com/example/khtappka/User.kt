package com.example.khtappka

import android.util.Log
import com.example.khtappka.Constants.Companion.API_URL
import android.os.StrictMode



class User (iusername: String, ipassword: String){
    val username = iusername
    val password = ipassword
    var id = ""
    lateinit var message: String
    lateinit var flag: String
    var email = ""

    fun login() : Boolean {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val response = khttp.post(API_URL + "/login", json = mapOf("username" to username, "password" to password))
        message = response.jsonObject.get("message") as String
        flag = response.jsonObject.get("flag") as String
        id = response.jsonObject.get("id").toString()
        Log.d("KHT", message)
        if (message == "success") return true
        return false
    }

    fun register() : Boolean {
        val response = khttp.post(API_URL + "/register", json = mapOf("username" to username, "password" to password, "email" to email, "flag" to "1"))
        message = response.jsonObject.get("message") as String
        flag = response.jsonObject.get("flag") as String
        id = response.jsonObject.get("id") as String
        if (message == "success") return true
        return false
    }

    fun getBalance(): String {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val response = khttp.post(API_URL + "/balance", json = mapOf("username" to username))
        return response.jsonObject.get("balance").toString()
    }

    fun transfer(amount : String, recipient: String): Boolean {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        Log.d("KHT", amount)
        Log.d("KHT", recipient)

        val response = khttp.post(API_URL + "/transfer", json = mapOf("username" to username, "target_username" to recipient, "amount" to amount))
        message = response.jsonObject.get("message").toString()
        if (message == "success") return true
        return false
    }

    fun checkId() : Boolean {
        val response = khttp.post(API_URL + "/user", json = mapOf("id" to id))
        message = response.jsonObject.get("message") as String
        flag = response.jsonObject.get("flag") as String
        id = response.jsonObject.get("id") as String
        email = response.jsonObject.get("email") as String
        if (message == "success") return true
        return false
    }

    fun testUrl() : Boolean {
        val response = khttp.get(API_URL + "/test")
        message = response.jsonObject.get("message") as String
        if (message == "success") return true
        return false
    }

}

/*
/test = GET - message - succes/unsucces
/login = POST - username: ... - password: ... (odpoved message - Usernamenotfound, Passwordiswrong, success, username, id, flag)
/register = POST - username: ... - password: ... - flag: ... - email: ...
/user = POST id: ... (message - success, IDnotfound, id, username, flag, email)
 */