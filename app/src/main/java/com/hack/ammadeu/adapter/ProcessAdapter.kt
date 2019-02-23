package com.hack.ammadeu.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ProcessAdapter (
    private val demandList: MutableList<Process>,
    private val context: Context
) : RecyclerView.Adapter<ProcessAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.demand_node, p0, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val note = demandList[p1]

        val titleEntry = p0.title.text.toString()
        val clientEntry = note.userClient

        p0.title.text = "$titleEntry $clientEntry"
        Picasso
            .with(context)
            .load(demandList[p1].img)
            .into(p0.image)

        p0.itemView.setOnClickListener {
            val intent = Intent(context, DemandDetailActivity::class.java)
            intent.putExtra("imgUrl", demandList[p1].img)
            intent.putExtra("phonenumber", demandList[p1].phonenumber)
            intent.putExtra("text", demandList[p1].text)
            intent.putExtra("userClient", demandList[p1].userClient)
            intent.putExtra("userStore", demandList[p1].userStore)
            it.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return demandList.size
    }


    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var title: TextView
        internal var image: ImageView

        init {
            title = view.findViewById(R.id.tv_userClient)
            image = view.findViewById(R.id.imgv_Demand)

        }
    }
}