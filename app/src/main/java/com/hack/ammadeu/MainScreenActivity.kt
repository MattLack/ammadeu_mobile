package com.hack.ammadeu

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.hack.ammadeu.fragment.FragmentAbout
import com.hack.ammadeu.fragment.FragmentProcess
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.app_bar_drawer.*

class MainScreenActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {

    lateinit var fragmentManager: FragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        // Set the toolbar as support action bar
        setSupportActionBar(toolbar)

        init()

        /*if (fragmentManager.fragments.isEmpty()) {
            val fragment = FragmentProcess.newInstance()
            replaceFragment(fragment)
        } else {
            replaceFragment(fragmentManager.fragments.last())
        }*/
    }


    private fun init() {
        //this object will help to replace the framelayout with the fragments
        fragmentManager = supportFragmentManager
        val toogle = ActionBarDrawerToggle(Activity(), drawer_layout, toolbar, R.string.nav_open, R.string.nav_close)
        drawer_layout.addDrawerListener(toogle)
        toogle.syncState()
        navigation_view.setNavigationItemSelectedListener(this)
    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_main_screen, fragment)
        fragmentTransaction.commit()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_home -> {
                fragmentManager.popBackStack("home", fragmentManager.backStackEntryCount + 1)
                fragmentManager.beginTransaction().replace(R.id.fragment_main_screen, FragmentProcess()).commit()
            }
            R.id.action_about_us -> {
                fragmentManager.popBackStack("about", fragmentManager.backStackEntryCount + 1)
                fragmentManager.beginTransaction().replace(R.id.fragment_main_screen, FragmentAbout()).commit()
            }
            /*R.id.action_upload -> {
                val intent = Intent(applicationContext, UploadActivity::class.java)
                startActivity(intent)
            }*/
            else -> {
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true

    }

    override fun onBackPressed() {
        if (fragmentManager.fragments.isEmpty()) {
            super.onBackPressed()
        } else {
            fragmentManager.popBackStackImmediate()
        }
    }
}
