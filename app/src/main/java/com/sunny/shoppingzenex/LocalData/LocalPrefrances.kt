package com.sunny.shoppingzenex.LocalData

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class LocalPrefrances(context: Context) {
    private var appSharedPrefs: SharedPreferences? = null
    private var prefsEditor: SharedPreferences.Editor? = null

    private val isLoggedOut = "LOG OUT"
    private val isUserLogin = " I am User"
    private val isMerchantLogin = "I am Merchant"


    private val productAddedCart ="productCart"


    var isLogout: Boolean?
        get() = appSharedPrefs!!.getBoolean(isLoggedOut, true)
        set(value) {
            value?.let {
                prefsEditor!!.putBoolean(isLoggedOut, it).commit() }
        }

    var userLogin: Boolean?
        get() = appSharedPrefs!!.getBoolean(isUserLogin, true)
        set(user) {
            if (user != null) {
                prefsEditor!!.putBoolean(isUserLogin, user).commit()
            }
        }

    var merchantLogin: Boolean?
        get() = appSharedPrefs!!.getBoolean(isMerchantLogin, true)
        set(merchant) {
            merchant?.let { prefsEditor!!.putBoolean(isMerchantLogin, it).commit() }
        }



    var productAdded : String?
        get() = appSharedPrefs!!.getString(productAddedCart, null)
        set(product) {
            product?.let { prefsEditor!!.putString(productAddedCart, it).commit() }
        }


    init {
        this.appSharedPrefs = context.getSharedPreferences(USER_PREFS, Activity.MODE_PRIVATE)
        this.prefsEditor = appSharedPrefs!!.edit()
    }


    @SuppressLint("CommitPrefEdits")
    fun reset(context: Context) {

        this.appSharedPrefs = context.getSharedPreferences(USER_PREFS, Activity.MODE_PRIVATE)
        this.prefsEditor = appSharedPrefs!!.edit().clear()
    }


    companion object {
        private const val USER_PREFS = "MY SHOPPING"
    }

}