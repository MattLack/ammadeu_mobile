package com.hack.ammadeu.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import com.hack.ammadeu.MainScreenActivity
import com.hack.ammadeu.R
import com.hack.ammadeu.adapter.ProcessAdapter
import com.hack.ammadeu.model.Process
import kotlinx.android.synthetic.main.fragment_process.*
import kotlinx.android.synthetic.main.list_layout.*

class FragmentProcess : Fragment() {


    private val TAG = "FragmentHome"
    private var mAdapter: ProcessAdapter? = null
    private var firestoreDB: FirebaseFirestore? = null
    val notesList = mutableListOf<Process>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_process, container, false)

        firestoreDB = FirebaseFirestore.getInstance()

        loadNotesList()

        return view
    }

    private fun loadNotesList() {
        firestoreDB!!.collection("Posts")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    for (doc in task.result!!) {

                        val t = doc.data as HashMap<String, String>
                        val note = Process(t["processNumber"], t["daysRamaining"], t["publicationDate"], t["priorityGrade"],
                            t["clientName"], t["discrimination"], t["opponent"], t["task"] )


                        notesList.add(note)
                    }

                    notesList.sortBy { it.daysRamaining.toString().toInt() }
                    mAdapter = ProcessAdapter(notesList, requireContext())
                    val mLayoutManager = LinearLayoutManager(requireContext())
                    list_processs.layoutManager = mLayoutManager
                    list_processs.itemAnimator = DefaultItemAnimator()
                    list_processs.adapter = mAdapter
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }

    }


    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, MainScreenActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

        fun newInstance() = FragmentProcess()
    }

}
