package com.hack.ammadeu

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToMainScreen(view: View) {
        val intent = Intent(applicationContext, MainScreenActivity::class.java)
        // intent.putExtra("input",editText.text.toString())
        startActivity(intent)
    }


}
