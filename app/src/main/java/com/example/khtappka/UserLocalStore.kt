package com.example.khtappka

import android.content.Context
import android.content.SharedPreferences

class UserLocalStore (ctx: Context){
    val context = ctx
    val SP_NAME = "userDataKHT"
    var userLocalDatabase = context.getSharedPreferences(SP_NAME,0)

    fun storeUserData (user: User){
        val spEditor = userLocalDatabase.edit()
        spEditor.putString("username", user.username)
        spEditor.putString("password", user.password)
        spEditor.apply()
    }

    fun getLoggedInUser(): User {
        val username = userLocalDatabase.getString("username", "")
        val password = userLocalDatabase.getString("password", "")

        val storedUser = User(username as String,password as String)

        return storedUser
    }

    fun setUserLoggedIn(loggedIn : Boolean) {
        val spEditor = userLocalDatabase.edit()
        spEditor.putBoolean("loggedIn", loggedIn)
        spEditor.apply()
    }

    fun getUserLoggedIn() : Boolean{
        return userLocalDatabase.getBoolean("loggedIn", false)
    }

    fun clearUserData() {
        val spEditor = userLocalDatabase.edit()
        spEditor.clear()
        spEditor.apply()
    }


}