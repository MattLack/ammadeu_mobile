package com.hack.ammadeu

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.process_detail_activity.*

class ProcessDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.process_detail_activity)

        loadInformationsInScreen()
    }


    fun loadInformationsInScreen() {

        val clientN = intent.getStringExtra("clientName")
        val DaysR = intent.getStringExtra("daysRamaining")
        val Discr = intent.getStringExtra("discrimination")
        val oppo = intent.getStringExtra("opponent")
        val priorit = intent.getStringExtra("priorityGrade")
        val processN = intent.getStringExtra("processNumber")
        val task = intent.getStringExtra("task")
        val publicatDat = intent.getStringExtra("publicationDate")


        tvDiscrimination.text = Discr
        tvNameClient.text = clientN
        tvGrPriorit.text = priorit
        tvNOpositor.text = oppo
        tvPublicationDate.text = publicatDat
        tvTasks.text = task
        tvNumProc.text = processN
        tvdays_ramaining.text = DaysR

       val DaysRI = DaysR.toString().toInt()

        when {
            DaysRI > 15 -> {
                tvdays_ramaining.setBackgroundColor(Color.GREEN)
            }
            DaysRI in 10..15 -> {
                tvdays_ramaining.setBackgroundColor(Color.GREEN)
            }
            DaysRI in 5..10 -> {
                tvdays_ramaining.setBackgroundColor(Color.YELLOW)
            }
            else -> {
                tvdays_ramaining.setBackgroundColor(Color.RED)
            }
        }

    }


    fun DemandDetailBacktoMain(view: View) {
        val intent = Intent(applicationContext, MainScreenActivity::class.java)
        startActivity(intent)
    }
}
