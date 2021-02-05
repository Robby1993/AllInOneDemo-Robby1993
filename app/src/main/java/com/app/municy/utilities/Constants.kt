package com.app.municy.utilities

import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.app.municy.model.Data
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.util.*

object Constants {

    /*  You’re accessing GitHub through SSH. First generate an SSH key pair; then add the public key to GitHub.
      Generate key pair:
      ssh-keygen -t rsa -b 4096 -C “youremail@example.com”*/

    //https://proandroiddev.com/viewbinding-with-kotlin-property-delegate-c907682e24c9#:~:text=Introduction%20to%20ViewBinding%20ViewBinding%20is%20a%20new%20feature,depend%20on%20Annotation%20Processing%20%28KAPT%29%20as%20Butterknife%20does.

    //Fragment back
    //https://stackoverflow.com/questions/40085608/how-to-pass-data-from-one-fragment-to-previous-fragment/40110415

    //Lib
    //https://github.com/florent37/SingleDateAndTimePicker


    @kotlin.jvm.JvmStatic
    lateinit var preferences: SharedPreferences
    private const val TAG = "SharedP"

    const val BASE_URL = "http://uploader.vijayadigipress.com/api/"
    private const val MySharedPref = "MysharedPref"
    val list = arrayListOf<Data>()
//    val list = arrayListOf<Data>()
    val map = hashMapOf<Int, Data>()
    private fun isAppOnForeground(context: Context): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appProcesses = activityManager.runningAppProcesses ?: return false
        val packageName = context.packageName
        for (appProcess in appProcesses) {
            if (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName == packageName) {
                return true
            }
        }
        return false
    }

    fun <T> put(`object`: T, key: String) {
        //Convert object to JSON String.
        val jsonString = GsonBuilder().create().toJson(`object`)
        //Save that String in SharedPreferences
        preferences.edit().putString(key, jsonString).apply()
    }

    inline fun <reified T> get(key: String): T? {
        //We read JSON String which was saved.
        val value = preferences.getString(key, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type Class < T >" is used to cast.
        return GsonBuilder().create().fromJson(value, T::class.java)
    }

    fun save(context: Context, key: String, value: String) {
        val sharedPref = context.getSharedPreferences(MySharedPref, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun save(context: Context, key: String, value: Int) {
        val sharedPref = context.getSharedPreferences(MySharedPref, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun save(context: Context, key: String, value: Boolean) {
        val sharedPref = context.getSharedPreferences(MySharedPref, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun save(context: Context, key: String, value: Long) {
        val sharedPref = context.getSharedPreferences(MySharedPref, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putLong(key, value)
        editor.apply()
    }


    fun save(context: Context, key: String?, value: Float) {
        val sharedpreferences = context.getSharedPreferences(MySharedPref, Context.MODE_PRIVATE)
        val editor = sharedpreferences.edit()
        editor.putFloat(key, value)
        editor.apply()
    }


    fun saveValue(context: Context, key: String?, value: Int) {
        val sharedpreferences = context.getSharedPreferences(MySharedPref, Context.MODE_PRIVATE)
        val editor = sharedpreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    @kotlin.jvm.JvmStatic
    fun getStringValue(context: Context, key: String?): String? {
        return context.getSharedPreferences(MySharedPref, Context.MODE_PRIVATE).getString(key, "")
    }

    fun getIntValue(context: Context, key: String?): Int {
        return context.getSharedPreferences(MySharedPref, Context.MODE_PRIVATE).getInt(key, -1)
    }

    fun getBooleanValue(context: Context, key: String?): Boolean {
        return context.getSharedPreferences(MySharedPref, Context.MODE_PRIVATE)
                .getBoolean(key, false)
    }

    fun getLongValue(context: Context, key: String?): Long {
        return context.getSharedPreferences(MySharedPref, Context.MODE_PRIVATE).getLong(key, 0)
    }

    fun toast(context: Context, msg: String) {
        Toast.makeText(
                context,
                msg, Toast.LENGTH_SHORT
        ).show()
    }

    fun greeting(context: Context): String {
        var displayGreeting = "";
        val c = Calendar.getInstance()
        when (c[Calendar.HOUR_OF_DAY]) {
            in 0..11 -> {
                displayGreeting = "Good Morning"
            }
            in 12..15 -> {
                displayGreeting = "Good Afternoon"
            }
            in 16..20 -> {
                displayGreeting = "Good Evening"
                // } else if (timeOfDay >= 21 && timeOfDay < 24) {
            }
            in 21..23 -> {
                displayGreeting = "Good Night"
            }
        }
        return displayGreeting;
    }

    fun shareAppActivity(context: Context) {
        var intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        if (intent == null) {
            // Bring user to the market or let them choose an app?
            intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("market://details?id=${context.packageName}")
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        context.startActivity(intent)
    }

    /*var cartList: List<String> = ArrayList<String>()

    cartList = getCartList()
    cartList.add(cart)
    var cartStr: String = gson.toJson(cartList)
    //Log.d("CART", cartStr);
    //Log.d("CART", cartStr);
    localStorage.setCart(cartStr)*/


    fun getList(context: Context): List<Data> {
        if (list.size > 0) {

        }
      /*  val value = getStringValue(context, "key")
        Log.d("getdata2222222222", "--$value")
        if (value != null) {
            val type = object : TypeToken<List<Data>>() {}.type
            try {
                return Gson().fromJson<List<Data>>(value, type)
            } catch (e: Exception) {
                Log.d("getdata2222222222", "--${e.printStackTrace()}")
            }

        }*/
        return list
    }

    fun setDataList(context: Context, data: Data) {
        /* val cartList = getList(context);
         if (cartList != null) {
             //cartList.add(data)
             val cartStr: String = Gson().toJson(cartList)
             save(context, "key", cartStr)
         } else {
             val cartStr: String = Gson().toJson(data)
             save(context, "key", cartStr)
         }*/
        map[data.value] = data
      //  list.add(data)
    }
}