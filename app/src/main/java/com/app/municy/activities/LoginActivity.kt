package com.app.municy.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.municy.HomeActivity
import com.app.municy.R
import com.app.municy.databinding.ActivityLoginBinding
import com.app.municy.databinding.BottomAgreeBinding
import com.app.municy.utilities.AESCrypt
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.security.GeneralSecurityException
import java.util.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        val mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.btnSign.setOnClickListener {
            //startActivity(Intent(this, MainActivity::class.java))
            startActivity(Intent(this, HomeActivity::class.java))
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
            finish()
        }

        mBinding.llReg.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
            //  finish()
        }
        /* val password = "password"
         val message = "hello world"
         try {
             val encryptedMsg: String = AESCrypt.encrypt(password, message)
         } catch (e: GeneralSecurityException) {
             //handle error
         }*/

        Log.d("Login : ----- ", getSecretKey())
        Log.d("Login password : ----- ", AESCrypt.encrypt("123456"))

        /* val dialogView: View = layoutInflater.inflate(R.layout.bottom_agree, null)
         val dialog = BottomSheetDialog(this)

         val mBindingDialog = BottomAgreeBinding.inflate(layoutInflater)
         // dialog.setContentView(dialogView)
         dialog.setContentView(mBindingDialog.root)
         (dialogView.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))
         mBindingDialog.tvAgree.setOnClickListener(View.OnClickListener {
             startActivity(Intent(this, LoginActivity::class.java))
             overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
             finish()
         })

         mBindingDialog.tvCancel.setOnClickListener(View.OnClickListener {
             finish()
         })
         dialog.show()*/

    }

    fun getSecretKey(): String {
        val calendar = Calendar.getInstance()
        calendar[Calendar.MILLISECOND] = 0 // Clear the millis part. Silly API.
        calendar[2010, 8, 14, 0, 0] = 0 // Note that months are 0-based
        val date = calendar.time
        //        long millis = date.getTime();
        val javaMillis = Date().time
        val ticks = 621355968000000000L + javaMillis * 10000
        println("TICKS = $ticks")
        val ticksString = ticks.toString()
        try {
            // return  AESCrypt.encrypt(ticksString, "")
            return AESCrypt.encrypt(ticksString)
        } catch (e: GeneralSecurityException) {
            //handle error
        }
        /* try {
             return AESEncryption.encrypt(ticksString)
         } catch (ex: Exception) {
             Log.d("LoginActivity :  ","----: "+ ex.message)
             ex.printStackTrace()
         }*/
        return ""
    }


}