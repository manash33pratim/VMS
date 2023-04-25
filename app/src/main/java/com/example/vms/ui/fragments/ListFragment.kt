package com.example.vms.ui.fragments

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vms.MainActivity
import com.example.vms.R
import com.example.vms.VisitorSlip
import java.io.File
import java.util.*
import kotlin.math.log


class ListFragment : Fragment() {
    lateinit var key: String
    lateinit var ed: EditText
    lateinit var datebtn: ImageButton

    private var mYear: Int = 0
    private var mMonth: Int = 0
    private var mDay: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.title = "Visitor List"

        val v = inflater.inflate(R.layout.fragment_list, container, false)
        key = ""
        val dateFilterButton = v.findViewById<Button>(R.id.date)

        val context = context as MainActivity

        val savedFiles = context.fileList()
        savedFiles.reverse()
        val lv = v.findViewById(R.id.list) as ListView
        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, savedFiles)
        lv.adapter = adapter
        ed = v.findViewById(R.id.abc)
        datebtn = v.findViewById(R.id.datebtn)

        //  -----------------------------------------------------
        datebtn.setOnClickListener {
        val c = Calendar.getInstance()
        mYear = c[Calendar.YEAR]
        mMonth = c[Calendar.MONTH]
        mDay = c[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(
            v.context,
            { view, year, monthOfYear, dayOfMonth
                ->

                if (monthOfYear + 1 < 10) {
                    if (dayOfMonth < 10) {
                        ed.setText("0$dayOfMonth-0${monthOfYear + 1}-$year")
                    } else {
                        ed.setText("$dayOfMonth-0${monthOfYear + 1}-$year")
                    }
                } else {
                    if (dayOfMonth < 10) {
                        ed.setText("0$dayOfMonth-${monthOfYear + 1}-$year")
                    } else {
                        ed.setText("$dayOfMonth-${monthOfYear + 1}-$year")
                    }
                }
            },

            mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }


//---------------------------------------------------------------
       // key =ed.text.toString()
        dateFilterButton.setOnClickListener {
            key =ed.text.toString()
            Log.d(TAG, "onCreateView: $key")
            searchFiles(key)





        }

        lv.setOnItemClickListener { parent, view, position, id ->
            val intValue = id.toInt()
            val item = lv.getItemAtPosition(intValue).toString()
            val intent = Intent(requireContext(), VisitorSlip::class.java)


            val bundle = Bundle()
            bundle.putString("myKey", item)
            intent.putExtras(bundle)

            startActivity(intent)
        }


        return v

    }

    private fun searchFiles(str:String) {
        val directory = requireContext().filesDir
        val files = directory.listFiles()
        val matchingFiles = mutableListOf<String>()
        for (file in files) {
            val fileContent = file.readText()
            if (file.isFile && fileContent.contains(str)) {
                matchingFiles.add(file.name.toString())

            }
        }
        if (matchingFiles.isEmpty()){
            Toast.makeText(context, "Visitor Not Found", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "Visitor Found", Toast.LENGTH_SHORT).show()
        }

        matchingFiles.reverse()
        val lv = requireView().findViewById(R.id.list) as ListView
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, matchingFiles)
        lv.adapter = adapter

    }
}