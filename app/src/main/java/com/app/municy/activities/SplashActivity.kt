package com.app.municy.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import com.app.municy.HomeActivity
import com.app.municy.MainActivity
import com.app.municy.R
import com.app.municy.databinding.BottomAgreeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /* Handler(Looper.getMainLooper()).postDelayed({
             startActivity(Intent(this, LoginActivity::class.java))
             overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
             finish()
         }, 3000)*/

        val dialogView: View = layoutInflater.inflate(R.layout.bottom_agree, null)
        val dialog = BottomSheetDialog(this)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(dialogView)
        //  (dialogView.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))
        (dialogView.parent as View).setBackgroundColor(Color.TRANSPARENT)

        (dialog.findViewById<View>(R.id.tvAgree) as TextView?)?.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
            finish()
        })

        (dialog.findViewById<View>(R.id.tvCancel) as TextView?)?.setOnClickListener(View.OnClickListener {
            finish()
        })

        dialog.show()


    }
}