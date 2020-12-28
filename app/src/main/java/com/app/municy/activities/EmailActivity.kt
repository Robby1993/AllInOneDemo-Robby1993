package com.app.municy.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.municy.databinding.ActivityEmailBinding

class EmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mBinding = ActivityEmailBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.llAssit.setOnClickListener {
            email(mBinding.tvAssi.text.toString().trim())
        }

        mBinding.llChiefDepartment.setOnClickListener {
            email(mBinding.tvChiefDepartment.text.toString().trim())
        }

        mBinding.llChiefOperation.setOnClickListener {
            email(mBinding.tvChiefOperation.text.toString().trim())
        }

        mBinding.llHelpDesk.setOnClickListener {
            email(mBinding.tvHelpDesk.text.toString().trim())
        }

        mBinding.llInfoTech.setOnClickListener {
            email(mBinding.tvInfoTech.text.toString().trim())
        }

        mBinding.llSupport.setOnClickListener {
            email(mBinding.tvSupport.text.toString().trim())
        }

        mBinding.llBack.setOnClickListener {
            finish()
        }
    }

    fun email(emailId: String) {
        Log.i("Send email", "")
        val TO = arrayOf("")
        val CC = arrayOf("")
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"
        //  emailIntent.putExtra(Intent.EXTRA_EMAIL, TO)
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailId)
        emailIntent.putExtra(Intent.EXTRA_CC, CC)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here")
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."))
            Log.i("email", "Finished sending email...")
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                this@EmailActivity,
                "There is no email client installed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}