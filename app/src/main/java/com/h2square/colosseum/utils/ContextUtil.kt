package com.h2square.colosseum.utils

import android.content.Context

class ContextUtil {
    companion object{
        private val prefName= "ColosseumPref"

        private val Token = "TOKEN"

        fun setToken(context: Context, token:String){
            val pref =context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(Token,token).apply()
        }
        fun getToken(context: Context):String{
            val pref =context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(Token,"")!!
        }

    }
}