package com.hack.ammadeu.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hack.ammadeu.MainScreenActivity
import com.hack.ammadeu.R
import com.hack.ammadeu.model.connector.JsonConnect

class FragmentProcess : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_process, container, false)

        get(view)

        return view
    }

    fun get(view: View) {

        val downloadData = JsonConnect()

        try {

            /*

            val base = radGroupBase.checkedRadioButtonId
            val buttonBase: RadioButton = findViewById(base)
            val textBase = buttonBase.text.toString()

            val convert = radGroupConvert.checkedRadioButtonId
            val buttonConvert: RadioButton = findViewById(convert)
            val textConvert = buttonConvert.text.toString()*/


            val url = "https://api.exchangeratesapi.io/latest?symbols="

            val sb = StringBuilder()
            //sb.append(url).append(textBase).append(",").append(textConvert)
            val c = sb.toString()

            downloadData.execute(c)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, MainScreenActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

        fun newInstance() = FragmentProcess()
    }
}
