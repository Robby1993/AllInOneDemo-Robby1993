package com.app.municy

import NavigationItemModel
import com.app.municy.adapter.NavigationRVAdapter
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.app.municy.databinding.ActivityHomeBinding
import com.app.municy.fragments.HomeFragment
import com.app.municy.fragments.ResourceFragment
import com.app.municy.fragments.SettingsFragment
import com.app.municy.fragments.SupportFragment
import com.app.municy.utilities.ClickListener
import com.app.municy.utilities.RecyclerTouchListener

class HomeActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityHomeBinding
    private lateinit var adapter: NavigationRVAdapter
    private var items = arrayListOf(
        NavigationItemModel("Home"),
        NavigationItemModel("Resources"),
        NavigationItemModel("Support"),
        NavigationItemModel("Settings"),
        NavigationItemModel("Exit"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  setContentView(R.layout.activity_home)
        //  drawerLayout = findViewById(R.id.drawer_layout)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        // Set the toolbar
        setSupportActionBar(mBinding.activityMainToolbar)

        // Setup Recyclerview's Layout
        mBinding.navigationRv.layoutManager = LinearLayoutManager(this)
        mBinding.navigationRv.setHasFixedSize(true)

        // Add Item Touch Listener
        mBinding.navigationRv.addOnItemTouchListener(RecyclerTouchListener(this, object :
            ClickListener {
            override fun onClick(view: View, position: Int) {
                when (position) {
                    0 -> {
                        // # Home Fragment
                        mBinding.activityMainToolbarTitle.text = getString(R.string.app_name)
                        val bundle = Bundle()
                        bundle.putString("fragmentName", "Home Fragment")
                        val homeFragment = HomeFragment()
                        homeFragment.arguments = bundle
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, homeFragment).commit()
                    }
                    1 -> {
                        mBinding.activityMainToolbarTitle.text = "Resource"
                        val bundle = Bundle()
                        bundle.putString("fragmentName", "Resource Fragment")
                        val musicFragment = ResourceFragment()
                        musicFragment.arguments = bundle
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, musicFragment).commit()
                    }
                    2 -> {
                        mBinding.activityMainToolbarTitle.text = "Support"
                        val bundle = Bundle()
                        bundle.putString("fragmentName", "Support Fragment")
                        val moviesFragment = SupportFragment()
                        moviesFragment.arguments = bundle
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, moviesFragment).commit()
                    }
                    3 -> {
                        mBinding.activityMainToolbarTitle.text = "Settings"
                        val bundle = Bundle()
                        bundle.putString("fragmentName", "Settings Fragment")
                        val booksFragment = SettingsFragment()
                        booksFragment.arguments = bundle
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, booksFragment).commit()
                    }
                    4 -> {
                        exitDialog()
                    }


                }
                // Don't highlight the 'Profile' and 'Like us on Facebook' item row
                // if (position != 6 && position != 4) {
                if (position != 4) {
                    updateAdapter(position)
                }
                Handler(mainLooper).postDelayed({
                    mBinding.drawerLayout.closeDrawer(GravityCompat.START)
                }, 200)
            }
        }))

        // Update Adapter with item data and highlight the default menu item ('Home' Fragment)
        updateAdapter(0)

        // Set 'Home' as the default fragment when the app starts
        mBinding.activityMainToolbarTitle.text = getString(R.string.app_name)
        val bundle = Bundle()
        bundle.putString("fragmentName", "Home Fragment")
        val homeFragment = HomeFragment()
        homeFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_content_id, homeFragment).commit()


        // Close the soft keyboard when you open or close the Drawer
        val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            mBinding.drawerLayout,
            mBinding.activityMainToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            override fun onDrawerClosed(drawerView: View) {
                // Triggered once the drawer closes
                super.onDrawerClosed(drawerView)
                try {
                    val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                } catch (e: Exception) {
                    e.stackTrace
                }
            }

            override fun onDrawerOpened(drawerView: View) {
                // Triggered once the drawer opens
                super.onDrawerOpened(drawerView)
                try {
                    val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                } catch (e: Exception) {
                    e.stackTrace
                }
            }
        }
        mBinding.drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        // Set background of Drawer
        mBinding.navigationLayout.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.colorPrimary
            )
        )
    }

    private fun exitDialog() {
        SweetAlertDialog(this@HomeActivity, SweetAlertDialog.WARNING_TYPE)
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

    private fun updateAdapter(highlightItemPos: Int) {
        adapter = NavigationRVAdapter(items, highlightItemPos)
        mBinding.navigationRv.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            // Checking for fragment count on back stack
            if (supportFragmentManager.backStackEntryCount > 0) {
                // Go to the previous fragment
                supportFragmentManager.popBackStack()
            } else {
                // Exit the app
                super.onBackPressed()
            }
        }
    }
}