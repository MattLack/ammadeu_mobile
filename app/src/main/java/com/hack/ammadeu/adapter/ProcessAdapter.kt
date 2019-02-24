package com.hack.ammadeu.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hack.ammadeu.ProcessDetailActivity
import com.hack.ammadeu.R
import com.hack.ammadeu.model.Process

class ProcessAdapter (
    private val processList: MutableList<Process>,
    private val context: Context
) : RecyclerView.Adapter<ProcessAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.process_list_item, p0, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val node = processList[p1]

        //val titleEntry = p0.title.text.toString()
        //val clientEntry = note.userClient

        val clientEntry = node.clientName
        val opponent = node.opponent
        val numProc = node.processNumber
        val days = node.daysRamaining.toString().toInt()
        val data = node.publicationDate

        val pCvsO = "$clientEntry X $opponent"
        p0.clientVsOpositor.text = pCvsO

        when {
            days > 15 -> {
                p0.daysRemaining.setBackgroundColor(Color.GREEN)
                p0.daysRemaining.text = days.toString()
            }
            days in 10..15 -> {
                p0.daysRemaining.setBackgroundColor(Color.GREEN)
                p0.daysRemaining.text = days.toString()
            }
            days in 10..15 -> {
                p0.daysRemaining.setBackgroundColor(Color.YELLOW)
                p0.daysRemaining.text = days.toString()
            }
            else -> {
                p0.daysRemaining.setBackgroundColor(Color.RED)
                p0.daysRemaining.text = days.toString()
            }
        }

        var numProText = p0.processNumber.text
        numProText = "$numProText  $numProc"

        p0.processNumber.text = numProText

        var dateText = p0.publicationDate.text
        dateText = "$dateText $data"

        p0.publicationDate.text = dateText

        p0.itemView.setOnClickListener {
            val intent = Intent(context, ProcessDetailActivity::class.java)
            intent.putExtra("clientName", node.clientName)
            intent.putExtra("daysRamaining", node.daysRamaining)
            intent.putExtra("discrimination", node.discrimination)
            intent.putExtra("opponent", node.opponent)
            intent.putExtra("priorityGrade",node.priorityGrade)
            intent.putExtra("processNumber",node.processNumber)
            intent.putExtra("task",node.task)
            intent.putExtra("publicationDate",node.publicationDate)
            it.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return processList.size
    }


    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var clientVsOpositor: TextView
        internal var processNumber: TextView
        internal var publicationDate: TextView
        internal var daysRemaining: TextView

        init {
            clientVsOpositor = view.findViewById(R.id.client_vs_opositor)
            processNumber = view.findViewById(R.id.process_number)
            publicationDate = view.findViewById(R.id.publication_date)
            daysRemaining = view.findViewById(R.id.days_ramaining)
        }
    }
}