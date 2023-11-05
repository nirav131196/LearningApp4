package com.loyaltywork.johnsoncustomertask.utils

import android.content.Context

object PreferenceHelper {

    private const val PreferenceName = "LoyaltyPreferenceHelper_CenturyKtn"


    fun setBooleanValue(context: Context, key: String, value: Boolean){
        val sharedPreferences = context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBooleanValue(context: Context, key: String): Boolean{
        val sharedPreferences = context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, false)
    }

    fun setStringValue(context: Context, key: String, value: String){
        val sharedPreferences = context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getStringValue(context: Context, key: String): String{
        val sharedPreferences = context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "")!!
    }
}