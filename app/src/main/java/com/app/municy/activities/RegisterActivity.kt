package com.app.municy.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.municy.R
import com.app.municy.databinding.ActivityLoginBinding
import com.app.municy.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_register)
        val mBinding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


        mBinding.llSignIn.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
            finish()

        })
    }
}