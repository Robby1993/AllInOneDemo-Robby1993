package com.app.municy.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.app.municy.R
import com.google.gson.Gson

class AgencyListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agency_list)

      /*  val cartResponse =
            Gson().fromJson(response.toString(), CartResponse::class.java)*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_notification -> {

                /*  val navController =
                      Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment);
                  navController.navigate(R.id.nav_noti);*/

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}