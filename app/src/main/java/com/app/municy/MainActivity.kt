package com.app.municy

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var mAppBarConfiguration: AppBarConfiguration;
    private var TAG: String = "MainActivity";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)

        mAppBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_home,
            R.id.nav_resource,
            R.id.nav_support,
            R.id.nav_settings,
            R.id.nav_exit
        ).setOpenableLayout(drawer).build()

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration)
        NavigationUI.setupWithNavController(navigationView, navController)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_exit -> {
                    exitDialog()
                }
                else -> {
                    // Make your navController object final above
                    // or call Navigation.findNavController() again here
                    NavigationUI.onNavDestinationSelected(menuItem, navController)
                }
            }
            drawer.closeDrawer(GravityCompat.START)
            true
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return (NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp())
    }

    private fun exitDialog() {
        SweetAlertDialog(this@MainActivity, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("Close Application")
            .setContentText("Are you sure you want to exit?")
            .setConfirmText("Exit")
            .setCancelText("Cancel")
            .setConfirmClickListener { sDialog ->
                sDialog.dismissWithAnimation()
                finish()
            }
            .setCancelClickListener { sDialog ->
                sDialog.dismissWithAnimation()
            }
            .show()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.notification -> {

                /*  val navController =
                      Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment);
                  navController.navigate(R.id.nav_noti);*/

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}